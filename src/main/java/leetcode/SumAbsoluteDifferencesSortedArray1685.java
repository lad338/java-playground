package leetcode;

public class SumAbsoluteDifferencesSortedArray1685 {

    class Solution {

        public int[] getSumAbsoluteDifferences(int[] nums) {
            int totalSum = 0;
            for (int n : nums) {
                totalSum += n;
            }
            int prefixSum = 0;
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int postfixSum = totalSum - prefixSum - nums[i];
                result[i] += postfixSum - (nums.length - 1 - i) * nums[i];
                result[i] += i * nums[i] - prefixSum;
                prefixSum += nums[i];
            }
            return result;
        }
    }
}
