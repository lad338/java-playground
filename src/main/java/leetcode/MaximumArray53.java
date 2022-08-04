package leetcode;

public class MaximumArray53 {

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
}
