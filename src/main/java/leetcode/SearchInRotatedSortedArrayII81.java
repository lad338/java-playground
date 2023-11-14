package leetcode;

public class SearchInRotatedSortedArrayII81 {

    class Solution {

        public boolean search(int[] nums, int target) {
            int L = 0;
            int R = nums.length - 1;

            while (L <= R) {
                int M = L + (R - L) / 2;
                if (
                    nums[L] == target || nums[R] == target || nums[M] == target
                ) {
                    return true;
                }

                if (nums[L] == nums[M]) {
                    L++;
                    continue;
                }

                if (nums[L] < nums[M]) {
                    if (nums[L] < target && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                    continue;
                }

                if (nums[M] < target && target < nums[R]) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }

            return false;
        }
    }
}
