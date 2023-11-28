package leetcode;

public class ProductArrayExceptSelf238 {

    class Solution {

        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;
            int[] result = new int[length];
            result[0] = nums[0];
            for (int i = 1; i < length; i++) {
                result[i] = result[i - 1] * nums[i];
            }

            int suffix = 1;
            for (int i = length - 1; i >= 0; i--) {
                result[i] = suffix * (i - 1 >= 0 ? result[i - 1] : 1);
                suffix *= nums[i];
            }

            return result;
        }
    }
}
