package leetcode;

public class MaximumAverageSubarrayI643 {

    class Solution {

        public double findMaxAverage(int[] nums, int k) {
            int max = Integer.MIN_VALUE;
            int subTotal = 0;
            for (int i = 0; i < k - 1; i++) {
                subTotal += nums[i];
            }

            for (int i = k - 1; i < nums.length; i++) {
                subTotal += nums[i];
                max = Math.max(max, subTotal);
                subTotal -= nums[i - k + 1];
            }

            return max / (double) k;
        }
    }
}
