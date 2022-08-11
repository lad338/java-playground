package leetcode;

public class JumpGameII45 {

    static class Solution {

        public int jump(int[] nums) {
            int result = 0;
            int maxDist = 0;
            int current = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                maxDist = Math.max(maxDist, i + nums[i]);
                if (i == current) {
                    result++;
                    current = maxDist;
                }
            }
            return result;
        }
    }
}
