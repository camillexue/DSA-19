import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] words = s.split("\\s+");

        int length = words.length;

        HashMap<String, Integer> wordFrequencies = new HashMap<>();

        for(int i = 0; i < length; i++) {
            wordFrequencies.put(words[i], wordFrequencies.getOrDefault(words[i], 0) + 1);
        }

        HashMap<Integer, List<String>> frequencyToWord = new HashMap<>();
        for(String str : wordFrequencies.keySet()) {
            int frequency = wordFrequencies.get(str);

            // get current words w/ that frequency
            List<String> listWords = frequencyToWord.getOrDefault(frequency, new ArrayList<>());
            // add new word to that list
            listWords.add(str);
            // update value for that freq key
            frequencyToWord.put(frequency, listWords);

        }

        List<String> parts = new ArrayList<>();

        // i = frequency of word
        for(int i = 1; i <= length; i++) {
            List<String> listWords = frequencyToWord.getOrDefault(i, new ArrayList<>());
            for(String word : listWords) {
                // repeat word as many times as frequency
                for (int x = 0; x < i; x++) {
                    parts.add(word);
                }
            }
        }
        // turn list into string
        String result = String.join(" ", parts);

        return result;
    }

}
