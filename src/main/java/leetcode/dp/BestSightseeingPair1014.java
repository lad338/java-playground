package leetcode.dp;

public class BestSightseeingPair1014 {

    class Solution {

        public int maxScoreSightseeingPair(int[] values) {
            int first = values[0];
            int max = 0;
            for (int i = 1; i < values.length; i++) {
                max = Math.max(max, values[i] - i + first);
                first = Math.max(first, values[i] + i);
            }

            return max;
        }
    }
}
