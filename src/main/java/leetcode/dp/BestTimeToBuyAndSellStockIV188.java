package leetcode.dp;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV188 {

    class Solution {

        public int maxProfit(int k, int[] prices) {
            int[] cost = new int[k];
            Arrays.fill(cost, prices[0]);
            int[] profit = new int[k];
            for (int price : prices) {
                for (int i = k - 1; i >= 0; i--) {
                    cost[i] =
                        Math.min(
                            cost[i],
                            price - (i - 1 >= 0 ? profit[i - 1] : 0)
                        );
                    profit[i] = Math.max(profit[i], price - cost[i]);
                }
            }
            return profit[k - 1];
        }
    }

    class ProfitCostSolution {

        class Solution {

            public int maxProfit(int k, int[] prices) {
                int[] cost = new int[k];
                Arrays.fill(cost, prices[0]);
                int[] profit = new int[k];
                for (int price : prices) {
                    for (int i = 0; i < k; i++) {
                        profit[i] = Math.max(profit[i], price - cost[i]);
                        cost[i] =
                            Math.min(
                                cost[i],
                                price - (i + 1 < k ? profit[i + 1] : 0)
                            );
                    }
                }
                return profit[0];
            }
        }
    }
}
