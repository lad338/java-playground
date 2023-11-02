package leetcode.dp;

public class PaintingTheWalls2742 {

    class Solution {

        public int paintWalls(int[] cost, int[] time) {
            int[][] cache = new int[cost.length][cost.length + 1];
            return helper(0, cost.length, cost, time, cache);
        }

        private int helper(
            int index,
            int remaining,
            int[] cost,
            int[] time,
            int[][] cache
        ) {
            if (remaining <= 0) {
                return 0;
            }

            if (index == time.length) {
                return 1_000_000_007;
            }

            if (cache[index][remaining] != 0) {
                return cache[index][remaining];
            }

            int paintCost =
                cost[index] +
                helper(
                    index + 1,
                    remaining - 1 - time[index],
                    cost,
                    time,
                    cache
                );
            int skipCost = helper(index + 1, remaining, cost, time, cache);
            int minCost = Math.min(paintCost, skipCost);
            cache[index][remaining] = minCost;

            return minCost;
        }
    }
}
