package leetcode;

public class MonotonicArray896 {

    class Solution {

        public boolean isMonotonic(int[] nums) {
            int i = 1;
            while (nums.length > i && nums[i] == nums[i - 1]) {
                i++;
            }

            if (i == nums.length) {
                return true;
            }

            boolean isDecreasing = nums[i - 1] > nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (isDecreasing && nums[j - 1] < nums[j]) {
                    return false;
                }
                if (!isDecreasing && nums[j - 1] > nums[j]) {
                    return false;
                }
            }

            return true;
        }
    }
}
