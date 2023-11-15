package leetcode.dp;

import java.util.*;

public class StoneGameII1140 {

    class Solution {

        int[] piles;
        Map<Boolean, Map<Integer, Map<Integer, Integer>>> cache = new HashMap<>();

        public int stoneGameII(int[] piles) {
            this.piles = piles;
            cache.put(true, new HashMap<>());
            cache.put(false, new HashMap<>());
            return helper(true, 0, 1);
        }

        private int helper(boolean isAliceTurn, int index, int M) {
            if (index == piles.length) {
                return 0;
            }

            cache.get(isAliceTurn).putIfAbsent(index, new HashMap<>());
            if (cache.get(isAliceTurn).get(index).containsKey(M)) {
                return cache.get(isAliceTurn).get(index).get(M);
            }

            int result = isAliceTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int subTotal = 0;

            for (int X = 1; X <= 2 * M; X++) {
                if (index + X > piles.length) {
                    break;
                }
                subTotal += piles[X + index - 1];

                if (isAliceTurn) {
                    result =
                        Math.max(
                            result,
                            subTotal + helper(false, index + X, Math.max(M, X))
                        );
                } else {
                    result =
                        Math.min(
                            result,
                            helper(true, index + X, Math.max(M, X))
                        );
                }
            }

            cache.get(isAliceTurn).get(index).put(M, result);
            return result;
        }
    }
}
