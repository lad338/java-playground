package leetcode.dp;

import java.util.Arrays;

public class HouseRobberII213 {

    class Solution {

        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }

            return Math.max(
                rob1(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                rob1(Arrays.copyOfRange(nums, 1, nums.length))
            );
        }

        public int rob1(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }

            int[] results = new int[nums.length];

            results[0] = nums[0];
            results[1] = nums[1];

            for (int i = 2; i < nums.length; i++) {
                if (i == 2) {
                    results[2] = results[0] + nums[2];
                } else {
                    results[i] =
                        nums[i] + Math.max(results[i - 2], results[i - 3]);
                }
            }

            return Math.max(results[nums.length - 1], results[nums.length - 2]);
        }
    }
}
