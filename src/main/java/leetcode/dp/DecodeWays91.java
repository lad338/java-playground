package leetcode.dp;

import java.util.Arrays;

public class DecodeWays91 {

    class TLESolution {

        public int numDecodings(String s) {
            if (s.length() <= 0) {
                return 1;
            }

            if (s.charAt(0) == '0') {
                return 0;
            }

            if (s.length() == 1) {
                return 1;
            }

            int result = 0;

            result += numDecodings(s.substring(1));

            int first2 = Integer.parseInt(s.substring(0, 2));
            if (first2 >= 10 && first2 <= 26) {
                result += numDecodings(s.substring(2));
            }

            return result;
        }
    }

    class DpSolution {

        public int numDecodings(String s) {
            if (s.charAt(0) == '0') {
                return 0;
            }

            int[] dp = new int[s.length() + 1];

            dp[0] = 1;
            dp[1] = s.charAt(0) == '0' ? 0 : 1;

            for (int i = 2; i <= s.length(); i++) {
                int first = Integer.parseInt(s.substring(i - 1, i));
                int second = Integer.parseInt(s.substring(i - 2, i));

                if (first != 0) {
                    dp[i] += dp[i - 1];
                }
                if (second >= 10 && second <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[s.length()];
        }
    }

    class RecursiveSolution {

        String s;
        int[] cache;

        public int numDecodings(String s) {
            this.s = s;
            cache = new int[s.length()];
            Arrays.fill(cache, -1);

            return helper(0);
        }

        private int helper(int index) {
            if (index == s.length()) {
                return 1;
            }

            if (cache[index] != -1) {
                return cache[index];
            }

            int ways = 0;

            int take1 = s.charAt(index) == '0' ? 0 : helper(index + 1);
            int take2 = index == s.length() - 1
                ? 0
                : (
                    (
                            s.charAt(index) == '1' ||
                            (
                                s.charAt(index) == '2' &&
                                s.charAt(index + 1) >= '0' &&
                                s.charAt(index + 1) <= '6'
                            )
                        )
                        ? helper(index + 2)
                        : 0
                );

            ways += take1;
            ways += take2;

            cache[index] = ways;
            return ways;
        }
    }
}
