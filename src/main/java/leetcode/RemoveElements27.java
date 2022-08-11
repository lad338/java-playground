package leetcode;

public class RemoveElements27 {

    static class Solution {

        public int removeElement(int[] nums, int val) {
            int backIndex = nums.length;
            for (int i = 0; i < nums.length && backIndex > i; i++) {
                if (nums[i] == val) {
                    backIndex--;
                    int backElement = nums[backIndex];
                    while (backElement == val && backIndex > i) {
                        backIndex--;
                        backElement = nums[backIndex];
                    }
                    nums[backIndex] = nums[i];
                    nums[i] = backElement;
                }
            }
            return backIndex;
        }
    }
}
