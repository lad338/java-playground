package leetcode.dp;

import java.util.*;

public class MaxLengthChainPair646 {

    class TopDownSolution {

        private int[] cache = new int[1];

        public int findLongestChain(int[][] pairs) {
            cache = new int[pairs.length];
            Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

            return helper(0, Integer.MIN_VALUE, pairs);
        }

        private int helper(int index, int previousEnd, int[][] pairs) {
            if (index >= pairs.length) {
                return 0;
            }

            if (cache[index] > 0) {
                return cache[index];
            }

            int max = 0;
            for (int i = index; i < pairs.length; i++) {
                if (pairs[i][0] > previousEnd) {
                    max = Math.max(max, 1 + helper(i + 1, pairs[i][1], pairs));
                }
            }

            cache[index] = max;
            return max;
        }
    }

    class GreedySolution {

        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
            int end = Integer.MIN_VALUE;
            int count = 0;

            for (int[] pair : pairs) {
                if (end < pair[0]) {
                    count++;
                    end = pair[1];
                }
            }

            return count;
        }
    }
}
