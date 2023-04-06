package leetcode;

import java.util.*;

public class LetterCombinations17 {

    class Solution {

        Map<Character, String> map = new HashMap<>() {
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }
        };

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return Collections.emptyList();
            }

            return permutate(Collections.singletonList(""), digits);
        }

        private List<String> permutate(
            List<String> prefix,
            String numbersLeft
        ) {
            if (numbersLeft.length() == 0) {
                return prefix;
            }

            String possibleChars = map.get(numbersLeft.charAt(0));
            List<String> results = new ArrayList<>();
            numbersLeft = numbersLeft.substring(1);
            for (String s : prefix) {
                for (int j = 0; j < possibleChars.length(); j++) {
                    String currentPrefix = s;
                    currentPrefix += possibleChars.charAt(j);
                    results.add(currentPrefix);
                }
            }
            if (numbersLeft.length() >= 1) {
                return permutate(results, numbersLeft);
            } else {
                return results;
            }
        }
    }
}
