package leetcode.dp;

public class BestTimeToBuyAndSellStockWithFee714 {

    class Solution {

        public int maxProfit(int[] prices, int fee) {
            int cost = prices[0];
            int profit = 0;
            for (int price : prices) {
                cost = Math.min(cost, price - profit);
                profit = Math.max(profit, price - cost - fee);
            }
            return profit;
        }
    }

    class ProfitCostSolution {

        public int maxProfit(int[] prices, int fee) {
            int cost = prices[0];
            int profit = 0;
            for (int price : prices) {
                profit = Math.max(profit, price - cost - fee);
                cost = Math.min(cost, price - profit);
            }
            return profit;
        }
    }

    class CostWithFeeSolution {

        public int maxProfit(int[] prices, int fee) {
            int profit = 0;
            int cost = prices[0] + fee;
            for (int price : prices) {
                profit = Math.max(profit, price - cost);
                cost = Math.min(cost, price - profit + fee);
            }
            return profit;
        }
    }
}
