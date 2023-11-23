package leetcode;

import java.util.*;

public class LengthOfLongestSubstring3 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int result = 1;
        List<Character> previousCharacters = new ArrayList<>();

        //TODO
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            //            System.out.println(i + ": " + currentChar);
            //            System.out.println("pre  | " + previousCharacters);
            if (previousCharacters.contains(currentChar)) {
                if (result < previousCharacters.size()) {
                    result = previousCharacters.size();
                }
                previousCharacters =
                    previousCharacters.subList(
                        previousCharacters.indexOf(currentChar) + 1,
                        previousCharacters.size()
                    );
            }
            //            System.out.println("post | " + previousCharacters);
            previousCharacters.add(currentChar);
            //            System.out.println("final| " + previousCharacters);

        }

        return Math.max(previousCharacters.size(), result);
    }

    class Solution {

        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int result = 0;
            int L = 0;
            char[] sArr = s.toCharArray();
            for (int R = 0; R < sArr.length; R++) {
                while (set.contains(sArr[R])) {
                    set.remove(sArr[L]);
                    L++;
                }
                set.add(sArr[R]);
                result = Math.max(result, 1 + R - L);
            }

            return result;
        }
    }
}
