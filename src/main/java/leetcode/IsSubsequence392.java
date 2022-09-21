package leetcode;

public class IsSubsequence392 {

    class Solution {

        public boolean isSubsequence(String s, String t) {
            if (s.length() == 0) {
                return true;
            }

            int indexS = 0;
            int indexT = 0;
            while (indexT < t.length()) {
                if (s.charAt(indexS) == t.charAt(indexT)) {
                    if (indexS == s.length() - 1) {
                        return true;
                    }
                    indexS++;
                }
                indexT++;
            }
            return false;
        }
    }

    class RecursiveSolution {

        public boolean isSubsequence(String s, String t) {
            if (s.length() == 0) {
                return true;
            }

            if (s.length() == 1) {
                return t.contains(s);
            }

            int index = t.indexOf(s.charAt(0));
            if (index < 0) {
                return false;
            }
            return isSubsequence(s.substring(1), t.substring(index + 1));
        }
    }
}
