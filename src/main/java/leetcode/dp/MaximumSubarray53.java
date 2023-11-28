package leetcode.dp;

public class MaximumSubarray53 {

    class Solution {

        public int maxSubArray(int[] nums) {
            int maximum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum > maximum) {
                    maximum = sum;
                }
                if (sum < 0) {
                    sum = 0;
                }
            }
            return maximum;
        }
    }

    class CleanerSolution {

        public int maxSubArray(int[] nums) {
            int result = Integer.MIN_VALUE;
            int sum = 0;
            for (int num : nums) {
                sum += num;
                result = Math.max(result, sum);
                sum = Math.max(sum, 0);
            }

            return result;
        }
    }
}
