package leetcode.dp;

public class BestTimeToBuyAndSellStockII122 {

    class Solution {

        public int maxProfit(int[] prices) {
            int[] buy = new int[prices.length];
            int[] sell = new int[prices.length];
            sell[0] = 0;
            buy[0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
                buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            }
            return sell[prices.length - 1];
        }
    }

    class GreedySolution {

        public int maxProfit(int[] prices) {
            int cost = prices[0];
            int profit = 0;
            for (int price : prices) {
                if (price > cost) {
                    profit += price - cost;
                }
                cost = price;
            }
            return profit;
        }
    }

    class CostProfitSolution {

        public int maxProfit(int[] prices) {
            int cost = prices[0];
            int profit = 0;
            for (int price : prices) {
                cost = Math.min(cost, price);
                if (price > cost) {
                    profit += price - cost;
                    cost = price;
                }
            }
            return profit;
        }
    }

    class ProfitCostSolution {

        class Solution {

            public int maxProfit(int[] prices) {
                int cost = prices[0];
                int profit = 0;
                for (int price : prices) {
                    if (price > cost) {
                        profit += price - cost;
                        cost = price;
                        continue;
                    }
                    cost = Math.min(cost, price);
                }
                return profit;
            }
        }
    }
}
