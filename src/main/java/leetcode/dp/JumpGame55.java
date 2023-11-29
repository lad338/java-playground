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

    class BackwardsGreedySolution {

        public boolean canJump(int[] nums) {
            if (nums.length == 1) {
                return true;
            }

            int goal = nums.length - 1;

            for (int i = nums.length - 2; i >= 0; i--) {
                if (i + nums[i] >= goal) {
                    goal = i;
                }
            }

            return goal == 0;
        }
    }

    //
    class ForwardGreedySolution {

        public boolean canJump(int[] nums) {
            int maxDistance = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (i <= maxDistance) {
                    maxDistance = Math.max(maxDistance, i + nums[i]);
                }
            }
            return maxDistance >= nums.length - 1;
        }
    }
}
