import java.util.*;

public class WordFrequencyUtils {
    public static void mergeWordFrequencies(Map<String, Integer> totalWordFrequency, Map<String, Integer> wordFrequency) {
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            totalWordFrequency.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    public static List<String> findRarestWords(Map<String, Integer> wordFrequency) {
        List<String> rarestWords = new ArrayList<>();
        int minFrequency = Collections.min(wordFrequency.values());
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (entry.getValue() == minFrequency) {
                rarestWords.add(entry.getKey());
            }
        }
        return rarestWords;
    }
}
