package leetcode;

public class FindMinimumRotatedSortedArray153 {

    class Solution {

        public int findMin(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            int L = 0;
            int R = nums.length - 1;

            while (L <= R) {
                int M = L + (R - L) / 2;

                int curr = nums[M];
                int prev = M != 0 ? nums[M - 1] : nums[nums.length - 1];
                if (prev > curr) {
                    return curr;
                }

                if (nums[R] <= curr) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }

            return 0;
        }
    }
}
