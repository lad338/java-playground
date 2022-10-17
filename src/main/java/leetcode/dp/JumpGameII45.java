package leetcode.dp;

public class JumpGameII45 {

    static class Solution {

        public int jump(int[] nums) {
            int result = 0;
            int potentialJump = 0;
            int currentPosition = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                potentialJump = Math.max(potentialJump, i + nums[i]);
                if (i == currentPosition) {
                    result++;
                    currentPosition = potentialJump;
                }
            }
            return result;
        }
    }
}
