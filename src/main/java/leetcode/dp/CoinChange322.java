package leetcode.dp;

import java.util.*;

public class CoinChange322 {

    class Solution {

        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }

            Set<Integer> coinSet = new HashSet<>();
            for (int c : coins) {
                coinSet.add(c);
            }
            coins = new int[coinSet.size()];

            List<Integer> coinList = new ArrayList<>(coinSet);
            for (int i = 0; i < coinList.size(); i++) {
                coins[i] = coinList.get(i);
            }

            Arrays.sort(coins);
            if (amount < coins[0]) {
                return -1;
            }

            int[] dp = new int[amount + 1];
            dp[0] = 0;

            for (int i = 0; i < coins[0]; i++) {
                dp[i] = -1;
            }
            int usingCoin = 0;
            dp[coins[0]] = 1;

            for (int i = coins[0] + 1; i <= amount; i++) {
                if (usingCoin + 1 < coins.length && i == coins[usingCoin + 1]) {
                    usingCoin++;
                    dp[i] = 1;
                } else {
                    int min = dp[i - coins[usingCoin]] < 0
                        ? -1
                        : (dp[i - coins[usingCoin]] + 1);

                    for (int j = usingCoin - 1; j >= 0; j--) {
                        int compare = dp[i - coins[j]] < 0
                            ? -1
                            : (dp[i - coins[j]] + 1);
                        if (compare > 0) {
                            if (min > 0) {
                                min = Math.min(compare, min);
                            } else {
                                min = compare;
                            }
                        }
                    }
                    dp[i] = min;
                }
            }

            return dp[amount] >= 0 ? dp[amount] : -1;
        }
    }
}
