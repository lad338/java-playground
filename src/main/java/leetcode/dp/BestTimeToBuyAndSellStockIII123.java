package leetcode.dp;

public class BestTimeToBuyAndSellStockIII123 {

    class Solution {

        public int maxProfit(int[] prices) {
            int buy1 = Integer.MIN_VALUE;
            int buy2 = Integer.MIN_VALUE;
            int sell1 = 0;
            int sell2 = 0;

            for (int price : prices) {
                sell2 = Math.max(sell2, buy2 + price);
                buy2 = Math.max(buy2, sell1 - price);
                sell1 = Math.max(sell1, buy1 + price);
                buy1 = Math.max(buy1, -price);
            }

            return sell2;
        }
    }

    class Solution2 {

        public int maxProfit(int[] prices) {
            int cost1 = prices[0];
            int cost2 = prices[0];
            int profit1 = 0;
            int profit2 = 0;
            for (int price : prices) {
                cost2 = Math.min(cost2, price - profit1);
                profit2 = Math.max(profit2, price - cost2);
                cost1 = Math.min(cost1, price);
                profit1 = Math.max(profit1, price - cost1);
            }
            return profit2;
        }
    }
}
