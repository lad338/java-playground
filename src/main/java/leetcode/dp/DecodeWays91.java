package leetcode.dp;

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
}
