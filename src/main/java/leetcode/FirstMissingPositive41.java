package leetcode;

public class FirstMissingPositive41 {

    class Solution {

        public int firstMissingPositive(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    nums[i] = 0;
                }
            }

            for (int num : nums) {
                int currentNum = Math.abs(num);
                if (1 <= currentNum && currentNum <= nums.length) {
                    int index = currentNum - 1;
                    if (nums[index] > 0) {
                        nums[index] *= -1;
                    } else if (nums[index] == 0) {
                        nums[index] = -(nums.length + 1);
                    }
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    return i + 1;
                }
            }
            return nums.length + 1;
        }
    }
}
