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
}
