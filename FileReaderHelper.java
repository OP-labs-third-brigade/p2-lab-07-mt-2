import java.io.*;
import java.util.*;

class FileReaderHelper {
    public static String[] readWords(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                words.addAll(Arrays.asList(lineWords));
            }
        }
        return words.toArray(new String[0]);
    }
}