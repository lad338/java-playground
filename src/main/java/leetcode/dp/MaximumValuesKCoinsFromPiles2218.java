package leetcode.dp;

import java.util.*;

public class MaximumValuesKCoinsFromPiles2218 {

    class Solution {

        List<List<Integer>> piles;
        Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

        public int maxValueOfCoins(List<List<Integer>> piles, int k) {
            this.piles = piles;

            return helper(0, k);
        }

        private int helper(int index, int coinsLeft) {
            if (index >= piles.size()) {
                return 0;
            }

            cache.putIfAbsent(index, new HashMap<>());
            if (cache.get(index).containsKey(coinsLeft)) {
                return cache.get(index).get(coinsLeft);
            }

            // Skip current coin
            int max = helper(index + 1, coinsLeft);
            int subTotal = 0;
            for (
                int i = 0;
                i < Math.min(coinsLeft, piles.get(index).size());
                i++
            ) {
                subTotal += piles.get(index).get(i);

                max =
                    Math.max(
                        max,
                        subTotal + helper(index + 1, coinsLeft - i - 1)
                    );
            }

            cache.get(index).put(coinsLeft, max);
            return max;
        }
    }
}
