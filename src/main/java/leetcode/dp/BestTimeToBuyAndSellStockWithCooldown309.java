package leetcode.dp;

import java.util.*;
import leetcode.Pair;

public class BestTimeToBuyAndSellStockWithCooldown309 {

    class Solution {

        Map<Pair<Integer, Boolean>, Integer> cache = new HashMap<>();

        public int maxProfit(int[] prices) {
            return dfs(prices, 0, true);
        }

        private int dfs(int[] prices, int i, boolean isBuying) { // true: buy, false: sell
            if (i >= prices.length) {
                return 0;
            }
            Pair<Integer, Boolean> current = new Pair<>(i, isBuying);
            if (cache.containsKey(current)) {
                return cache.get(current);
            }

            int cooldown = dfs(prices, i + 1, isBuying);
            int net =
                dfs(prices, i + (isBuying ? 1 : 2), !isBuying) +
                (isBuying ? -prices[i] : prices[i]);
            cache.put(current, Math.max(net, cooldown));
            return cache.get(current);
        }
    }
}
