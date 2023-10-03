package leetcode.dp;

public class PalindromicSubstrings647 {

    class Solution {

        public int countSubstrings(String s) {
            int newStringCount = s.length() * 2 - 1;
            int result = s.length();

            for (int i = 0; i < newStringCount; i++) {
                int j = 1;
                boolean isDone = false;
                while (!isDone) {
                    if (i % 2 == 0) {
                        if (i - (j * 2) >= 0 && i + (j * 2) < newStringCount) {
                            if (
                                s.charAt((i - (j * 2)) / 2) ==
                                s.charAt((i + (j * 2)) / 2)
                            ) {
                                result++;
                                j++;
                                continue;
                            }
                        }
                    } else {
                        if (
                            i - (j * 2 - 1) >= 0 &&
                            i + (j * 2 - 1) < newStringCount
                        ) {
                            if (
                                s.charAt((i - (j * 2 - 1)) / 2) ==
                                s.charAt((i + (j * 2 - 1)) / 2)
                            ) {
                                result++;
                                j++;
                                continue;
                            }
                        }
                    }
                    isDone = true;
                }
            }

            return result;
        }
    }
}
