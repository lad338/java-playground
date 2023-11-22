package leetcode;

public class RemoveDuplicateFromSortedArrayII80 {

    class Solution {

        public int removeDuplicates(int[] nums) {
            int fillIndex = 0;
            int L = 0;
            int R = 0;
            while (R < nums.length) {
                while (R < nums.length && nums[R] == nums[L]) {
                    if (R - L < 2) {
                        nums[fillIndex] = nums[R];
                        fillIndex++;
                    }
                    R++;
                }
                L = R;
            }

            return fillIndex;
        }
    }
}
