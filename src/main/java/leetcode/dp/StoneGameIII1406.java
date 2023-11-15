package leetcode.dp;

import java.util.*;

public class StoneGameIII1406 {

    class Solution {

        int[] stoneValue;
        Map<Boolean, Map<Integer, Integer>> cache = new HashMap<>();

        public String stoneGameIII(int[] stoneValue) {
            this.stoneValue = stoneValue;
            cache.put(true, new HashMap<>());
            cache.put(false, new HashMap<>());
            int result = helper(true, 0);
            if (result > 0) {
                return "Alice";
            } else if (result < 0) {
                return "Bob";
            } else {
                return "Tie";
            }
        }

        private int helper(boolean isAliceTurn, int index) {
            if (index == stoneValue.length) {
                return 0;
            }

            if (cache.get(isAliceTurn).containsKey(index)) {
                return cache.get(isAliceTurn).get(index);
            }

            int result = isAliceTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int subTotal = 0;

            for (int i = 0; i < 3; i++) {
                if (index + i >= stoneValue.length) {
                    break;
                }
                subTotal += stoneValue[i + index];
                if (isAliceTurn) {
                    result =
                        Math.max(
                            result,
                            helper(false, index + 1 + i) + subTotal
                        );
                } else {
                    result =
                        Math.min(
                            result,
                            helper(true, index + 1 + i) - subTotal
                        );
                }
            }

            cache.get(isAliceTurn).put(index, result);

            return result;
        }
    }
}
