package leetcode;

public class SortColors75 {

    class Solution {

        public void sortColors(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int i = 0;
            while (i <= right) {
                int current = nums[i];
                if (current == 0) {
                    swap(nums, i, left);
                    left++;
                    i++;
                } else if (current == 2) {
                    swap(nums, i, right);
                    right--;
                } else {
                    i++;
                }
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
