package leetcode.dp;

import java.util.*;

public class StoneGame877 {

    class Solution {

        Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
        int[] piles;

        public boolean stoneGame(int[] piles) {
            this.piles = piles;

            return helper(0, piles.length - 1) > 0;
        }

        private int helper(int L, int R) {
            if (L > R) {
                return 0;
            }

            cache.putIfAbsent(L, new HashMap<>());
            if (cache.get(L).containsKey(R)) {
                return cache.get(L).get(R);
            }

            boolean isAliceTurn = (R - L) % 2 == 1;

            if (isAliceTurn) {
                cache
                    .get(L)
                    .put(
                        R,
                        Math.max(
                            helper(L + 1, R) + piles[L],
                            helper(L, R - 1) + piles[R]
                        )
                    );
            } else {
                cache
                    .get(L)
                    .put(
                        R,
                        Math.min(
                            helper(L + 1, R) - piles[L],
                            helper(L, R - 1) - piles[R]
                        )
                    );
            }

            return cache.get(L).get(R);
        }
    }
}
