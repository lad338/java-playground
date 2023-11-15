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

    class NeetCodeSolution {

        int[] stoneValue;
        int[] cache;

        public String stoneGameIII(int[] stoneValue) {
            this.stoneValue = stoneValue;
            this.cache = new int[stoneValue.length];
            Arrays.fill(cache, Integer.MIN_VALUE);
            int result = helper(0);

            if (result > 0) {
                return "Alice";
            } else if (result < 0) {
                return "Bob";
            } else {
                return "Tie";
            }
        }

        private int helper(int index) {
            if (index == stoneValue.length) {
                return 0;
            }

            if (cache[index] != Integer.MIN_VALUE) {
                return cache[index];
            }

            int subTotal = 0;
            int result = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                if (index + i >= stoneValue.length) {
                    break;
                }
                subTotal += stoneValue[i + index];
                result = Math.max(result, subTotal - helper(i + index + 1));
            }

            cache[index] = result;

            return result;
        }
    }
}
