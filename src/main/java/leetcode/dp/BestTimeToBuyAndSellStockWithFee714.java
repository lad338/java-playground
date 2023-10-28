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
}
