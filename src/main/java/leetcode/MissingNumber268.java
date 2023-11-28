package leetcode;

public class MissingNumber268 {

    class Solution {

        public int missingNumber(int[] nums) {
            int result = nums.length;
            for (int i = 0; i < nums.length; i++) {
                result += i;
                result -= nums[i];
            }
            return result;
        }
    }

    class XORSolution {

        public int missingNumber(int[] nums) {
            int result = 0;
            for (int i = 0; i <= nums.length; i++) {
                result ^= i;
            }
            for (int num : nums) {
                result ^= num;
            }
            return result;
        }
    }
}
