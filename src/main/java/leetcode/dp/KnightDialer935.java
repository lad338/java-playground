package leetcode.dp;

import java.util.Arrays;

public class KnightDialer935 {

    class Solution {

        int MOD = (int) 1e9 + 7;

        int[][] possibleMoves = new int[][] {
            new int[] { 4, 6 },
            new int[] { 6, 8 },
            new int[] { 7, 9 },
            new int[] { 4, 8 },
            new int[] { 0, 3, 9 },
            new int[] {},
            new int[] { 0, 1, 7 },
            new int[] { 2, 6 },
            new int[] { 1, 3 },
            new int[] { 2, 4 },
        };

        public int knightDialer(int n) {
            int[] cache = new int[10];
            Arrays.fill(cache, 1);

            for (int i = 2; i <= n; i++) {
                int[] newCache = new int[10];
                for (int j = 0; j < 10; j++) {
                    int count = 0;
                    for (int possible : possibleMoves[j]) {
                        count += cache[possible];
                        count %= MOD;
                    }
                    newCache[j] = count;
                }
                cache = newCache;
            }

            int result = 0;
            for (int num : cache) {
                result += num;
                result %= MOD;
            }
            return result;
        }
    }
}
