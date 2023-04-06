package leetcode;

public class SearchInRotatedSortedArray33 {

    class Solution {

        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            if (nums.length == 1) {
                return nums[0] == target ? 0 : -1;
            }

            int a = 0;
            int b = nums.length - 1;

            while (a < b) {
                int mid = (a + b) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[b] == target) {
                    return b;
                }
                if (nums[a] == target) {
                    return a;
                }

                if (nums[b] >= nums[mid]) {
                    if (target > nums[mid] && target <= nums[b]) {
                        a = mid + 1;
                    } else {
                        b = mid - 1;
                    }
                } else {
                    if (target < nums[mid] && target >= nums[a]) {
                        b = mid - 1;
                    } else {
                        a = mid + 1;
                    }
                }
            }

            return -1;
        }
    }
}
