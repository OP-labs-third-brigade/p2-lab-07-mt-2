import java.util.*;
import java.util.concurrent.*;
import java.io.IOException;

public class WordFrequencyAnalyzer {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Використання: java WordFrequencyAnalyzer <ім'я_файлу1> <ім'я_файлу2> ...");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(args.length);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (String filename : args) {
            final String finalFilename = filename;
            Callable<Map<String, Integer>> task = () -> {
                String[] words = FileReaderHelper.readWords(finalFilename);
                return WordFrequencyCounter.countWordFrequency(words);
            };
            futures.add(executor.submit(task));
        }

        Map<String, Integer> totalWordFrequency = new HashMap<>();

        for (Future<Map<String, Integer>> future : futures) {
            try {
                Map<String, Integer> wordFrequency = future.get();
                WordFrequencyUtils.mergeWordFrequencies(totalWordFrequency, wordFrequency);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Помилка обробки файлу: " + e.getMessage());
            }
        }

        executor.shutdown();

        List<String> rarestWords = WordFrequencyUtils.findRarestWords(totalWordFrequency);
        System.out.println("Найрідші слова: " + rarestWords);
    }
}
