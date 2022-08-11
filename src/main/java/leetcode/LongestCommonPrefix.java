package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestCommonPrefix {

    static class Solution {

        public String longestCommonPrefix(String[] strs) {
            StringBuilder result = new StringBuilder();
            Set<String> previousSet = new HashSet<>(Arrays.asList(strs));
            Set<String> currentSet = new HashSet<>();
            while (true) {
                Character currentCharacter = null;
                for (String str : previousSet) {
                    if (str.length() == 0) {
                        return result.toString();
                    }
                    char firstCharacter = str.charAt(0);
                    if (
                        currentCharacter != null &&
                        firstCharacter != currentCharacter
                    ) {
                        return result.toString();
                    }
                    currentCharacter = firstCharacter;
                    currentSet.add(str.substring(1));
                }
                result.append(currentCharacter);
                previousSet = currentSet;
                currentSet = new HashSet<>();
            }
        }
    }
}
