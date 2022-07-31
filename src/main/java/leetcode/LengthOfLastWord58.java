package leetcode;

public class LengthOfLastWord58 {
    static class Solution {
        public int lengthOfLastWord(String s) {
            boolean isWord = Character.isAlphabetic(s.charAt(0));
            int result = isWord? 1 : 0;
            for (int i = 1; i < s.length(); i++) {
                char current = s.charAt(i);
                if (Character.isAlphabetic(current)) {
                    if (isWord) {
                        result ++;
                    } else {
                        result = 1;
                        isWord = true;
                    }
                } else {
                    if (isWord) {
                        isWord = false;
                    }
                }
            }
            return result;
        }
    }

    static class ReverseSolution {
        public int lengthOfLastWord(String s) {
            boolean isWord = Character.isAlphabetic(s.charAt(s.length() - 1));
            int result = isWord? 1 : 0;
            for (int i = 1; i < s.length(); i++) {
                char current = s.charAt(s.length() - 1 - i);
                if (Character.isAlphabetic(current)) {
                    if (isWord) {
                        result ++;
                    } else {
                        result = 1;
                        isWord = true;
                    }
                } else {
                    if (isWord) {
                        return result;
                    }
                }
            }
            return result;
        }
    }

    static class JavaSolution {
        public int lengthOfLastWord(String s) {
            // A more JAVAish solution
            s = s.trim();

            int lastSpace = s.lastIndexOf(" ");

            if (lastSpace == -1) {
                return s.length();
            }

            return s.length() - lastSpace - 1;
        }
    }
}
