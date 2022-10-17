package leetcode.dp;

public class JumpGame55 {

    class Solution {

        public boolean canJump(int[] nums) {
            if (nums.length <= 1) {
                return true;
            }

            boolean[] reversedCheck = new boolean[nums.length - 1];
            int end = nums.length - 1;

            for (int i = nums.length - 2; i >= 0; i--) {
                int distanceToEnd = end - i;
                if (nums[i] >= distanceToEnd) {
                    reversedCheck[i] = true;
                    end = i;
                }
            }
            return reversedCheck[0];
        }
    }
}
