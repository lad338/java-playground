package leetcode;

import java.util.*;

public class FinalPricesWithDiscount1475 {

    class Solution {

        public int[] finalPrices(int[] prices) {
            int[] result = new int[prices.length];

            for (int i = 0; i < prices.length; i++) {
                boolean hasDiscount = false;
                for (int j = i + 1; j < prices.length && !hasDiscount; j++) {
                    if (prices[j] <= prices[i]) {
                        result[i] = prices[i] - prices[j];
                        hasDiscount = true;
                    }
                }
                if (!hasDiscount) {
                    result[i] = prices[i];
                }
            }

            return result;
        }
    }

    class MonotonicStackSolution {

        public int[] finalPrices(int[] prices) {
            int[] result = new int[prices.length];

            Deque<Integer> stack = new ArrayDeque<>();

            for (int i = 0; i < prices.length; i++) {
                int price = prices[i];

                while (stack.size() > 0) {
                    if (price <= prices[stack.peek()]) {
                        int index = stack.pop();
                        result[index] = prices[index] - price;
                    } else {
                        break;
                    }
                }
                stack.push(i);
            }

            while (stack.size() > 0) {
                int index = stack.pop();
                result[index] = prices[index];
            }

            return result;
        }
    }
}
