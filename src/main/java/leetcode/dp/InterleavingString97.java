package leetcode.dp;

import java.util.*;

public class InterleavingString97 {

    class Solution {

        Map<Integer, Map<Integer, Boolean>> dp = new HashMap<>();

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            return recursive(s1, s2, s3, 0, 0);
        }

        private boolean recursive(
            String s1,
            String s2,
            String s3,
            int i1,
            int i2
        ) {
            if (i1 == s1.length() && i2 == s2.length()) {
                return true;
            }

            if (dp.containsKey(i1) && dp.get(i1).containsKey(i2)) {
                return dp.get(i1).get(i2);
            }

            char target = s3.charAt(i1 + i2);

            if (i1 < s1.length() && target == s1.charAt(i1)) {
                boolean result = recursive(s1, s2, s3, i1 + 1, i2);
                if (result) {
                    return true;
                }
            }

            if (i2 < s2.length() && target == s2.charAt(i2)) {
                boolean result = recursive(s1, s2, s3, i1, i2 + 1);
                if (result) {
                    return true;
                }
            }

            Map<Integer, Boolean> map = dp.getOrDefault(i1, new HashMap<>());
            map.put(i2, false);
            dp.put(i1, map);
            return false;
        }
    }

    class TrueDPSolution {

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
            dp[s1.length()][s2.length()] = true;

            for (int i = s1.length(); i >= 0; i--) {
                for (int j = s2.length(); j >= 0; j--) {
                    if (i == s1.length() && j == s2.length()) {
                        continue;
                    }

                    char target = s3.charAt(i + j);

                    if (
                        i < s1.length() &&
                        target == s1.charAt(i) &&
                        dp[i + 1][j]
                    ) {
                        dp[i][j] = true;
                    }
                    if (
                        !dp[i][j] &&
                        j < s2.length() &&
                        target == s2.charAt(j) &&
                        dp[i][j + 1]
                    ) {
                        dp[i][j] = true;
                    }
                }
            }

            return dp[0][0];
        }
    }
}
