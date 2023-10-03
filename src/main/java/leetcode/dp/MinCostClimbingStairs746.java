package leetcode.dp;

public class MinCostClimbingStairs746 {

    class Solution {

        public int minCostClimbingStairs(int[] cost) {
            int goal = cost.length;

            int[] dp = new int[goal];

            for (int i = 0; i < goal; i++) {
                if (i < 2) {
                    dp[i] = cost[goal - 1 - i];
                } else {
                    dp[i] = cost[goal - 1 - i] + Math.min(dp[i - 1], dp[i - 2]);
                }
            }

            return Math.min(dp[goal - 1], dp[goal - 2]);
        }
    }

    class Solution2 {

        public int minCostClimbingStairs(int[] cost) {
            if (cost.length == 1) {
                return cost[0];
            }

            int[] dp = new int[cost.length];
            dp[cost.length - 1] = cost[cost.length - 1];
            dp[cost.length - 2] = cost[cost.length - 2];

            for (int i = cost.length - 3; i >= 0; i--) {
                dp[i] = Math.min(cost[i] + dp[i + 2], cost[i] + dp[i + 1]);
            }

            return Math.min(dp[0], dp[1]);
        }
    }
}
