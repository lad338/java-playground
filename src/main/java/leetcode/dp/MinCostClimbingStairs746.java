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
}
