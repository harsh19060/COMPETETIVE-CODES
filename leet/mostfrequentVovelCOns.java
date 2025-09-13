// ou are given a string s consisting of lowercase English letters ('a' to 'z').

// Your task is to:

// Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
// Find the consonant (all other letters excluding vowels) with the maximum frequency.
// Return the sum of the two frequencies.

// Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If there are no vowels or no consonants in the string, consider their frequency as 0.

// The frequency of a letter x is the number of times it occurs in the string.
import java.util.*;

public class mostfrequentVovelCOns {

    public int maxFreqSum(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        int maxFreqvov = 0;
        String vovel = "aeiou";

        for (char c : s.toCharArray()) {
            int freq = freqMap.getOrDefault(c, 0) + 1;
            freqMap.put(c, freq);
            if (vovel.indexOf(c) != -1) {
                maxFreqvov = Math.max(maxFreqvov, freq);
            } else {
                maxFreq = Math.max(maxFreq, freq);
            }
        }
        return maxFreq + maxFreqvov;
    }

}
