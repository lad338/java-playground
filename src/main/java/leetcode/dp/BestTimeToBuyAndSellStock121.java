package leetcode.dp;

public class BestTimeToBuyAndSellStock121 {

    class Solution {

        public int maxProfit(int[] prices) {
            int buy = Integer.MIN_VALUE;
            int sell = 0;

            for (int i = 0; i < prices.length; i++) {
                sell = Math.max(sell, buy + prices[i]);
                buy = Math.max(buy, -prices[i]);
            }

            return sell;
        }
    }

    class Solution2 {

        public int maxProfit(int[] prices) {
            int cost = prices[0];
            int result = 0;

            for (int price : prices) {
                result = Math.max(result, price - cost);
                cost = Math.min(cost, price);
            }
            return result;
        }
    }

    class ProfitCostSolution {

        public int maxProfit(int[] prices) {
            int cost = prices[0];
            int profit = 0;

            for (int price : prices) {
                profit = Math.max(profit, price - cost);
                cost = Math.min(cost, price);
            }
            return profit;
        }
    }
}
