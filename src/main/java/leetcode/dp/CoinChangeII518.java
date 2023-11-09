package leetcode.dp;

public class CoinChangeII518 {

    class Solution {

        public int change(int amount, int[] coins) {
            int[][] cache = new int[amount + 1][coins.length];

            for (int i = 0; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (i == 0) {
                        cache[0][j] = 1;
                        continue;
                    }

                    if (j == 0) {
                        if (i % coins[j] == 0) {
                            cache[i][0] = 1;
                        }
                        continue;
                    }

                    cache[i][j] += cache[i][j - 1];
                    int diff = i - coins[j];
                    if (diff >= 0) {
                        cache[i][j] += cache[diff][j];
                    }
                }
            }

            return cache[amount][coins.length - 1];
        }
    }
}
