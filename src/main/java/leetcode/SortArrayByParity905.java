package leetcode;

public class SortArrayByParity905 {

    class Solution {

        public int[] sortArrayByParity(int[] nums) {
            int l = 0;
            int r = nums.length - 1;
            while (r >= 0 && nums[r] % 2 == 1) {
                r--;
            }
            while (l < nums.length && nums[l] % 2 == 0) {
                l++;
            }
            while (l < r) {
                if (nums[l] % 2 == 0) {
                    l++;
                } else {
                    int tmp = nums[l];
                    nums[l] = nums[r];
                    nums[r] = tmp;
                    r--;
                }
            }

            return nums;
        }
    }
}
