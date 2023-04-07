package leetcode.dp;

public class PerfectSquares279 {

    class Solution {

        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;

            int nextLength = 2;
            int next = 4;
            int previousLength = 1;
            int previous = 1;

            for (int i = 2; i <= n; i++) {
                if (next == i) {
                    dp[i] = 1;
                    previousLength = nextLength;
                    previous = previousLength * previousLength;
                    nextLength++;
                    next = nextLength * nextLength;
                } else {
                    int min = 1 + dp[(i - previous)];
                    for (int j = previousLength; j > 1; j--) {
                        min = Math.min(min, dp[i - j * j] + 1);
                    }

                    dp[i] = min;
                }
            }
            System.out.println(previous);

            return dp[n];
        }
    }
}
