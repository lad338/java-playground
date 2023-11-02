package leetcode.dp;

public class WaysStaySamePlaceAfterSteps1269 {

    class Solution {

        private int[][] cache = new int[1][1];

        private final int mod = 1_000_000_007;

        public int numWays(int steps, int arrLen) {
            cache = new int[steps + 1][Math.min(steps / 2 + 1, arrLen)];
            for (int i = 0; i < cache.length; i++) {
                for (int j = 0; j < cache[0].length; j++) {
                    cache[i][j] = -1;
                }
            }

            return helper(steps, 0, Math.min(steps / 2 + 1, arrLen));
        }

        private int helper(int steps, int index, int boundary) {
            if (steps == 0 && index == 0) {
                return 1;
            }
            if (steps == 0) {
                return 0;
            }
            if (cache[steps][index] != -1) {
                return cache[steps][index];
            }
            int sum = helper(steps - 1, index, boundary) % mod;

            if (index - 1 >= 0) {
                sum = (sum + helper(steps - 1, index - 1, boundary)) % mod;
            }

            if (index + 1 < boundary) {
                sum = (sum + helper(steps - 1, index + 1, boundary)) % mod;
            }
            cache[steps][index] = sum;
            return sum;
        }
    }
}
