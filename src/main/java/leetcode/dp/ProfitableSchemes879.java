package leetcode.dp;

import java.util.Arrays;

public class ProfitableSchemes879 {

    class Solution {

        int MOD = (int) 1e9 + 7;
        int minProfit;
        int[] group;
        int[] profit;
        int[][][] cache;

        public int profitableSchemes(
            int n,
            int minProfit,
            int[] group,
            int[] profit
        ) {
            this.minProfit = minProfit;
            this.group = group;
            this.profit = profit;
            cache = new int[n + 1][group.length][minProfit + 1];
            for (int[][] a1 : cache) {
                for (int[] a2 : a1) {
                    Arrays.fill(a2, -1);
                }
            }

            return helper(n, 0, 0);
        }

        private int helper(int n, int index, int p) {
            if (index >= group.length) {
                if (p >= minProfit) {
                    return 1;
                } else {
                    return 0;
                }
            }

            if (cache[n][index][Math.min(p, minProfit)] != -1) {
                return cache[n][index][Math.min(p, minProfit)];
            }

            // Picking

            long result = 0;

            if (n - group[index] >= 0) {
                result +=
                    helper(n - group[index], index + 1, p + profit[index]);
            }

            // Not picking
            result += helper(n, index + 1, p);

            result %= MOD;

            //put in cache

            cache[n][index][Math.min(p, minProfit)] = (int) result;

            return (int) result;
        }
    }
}
