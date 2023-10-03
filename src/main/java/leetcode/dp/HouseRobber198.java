package leetcode.dp;

public class HouseRobber198 {

    class Solution {

        public int rob(int[] nums) {
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

    class Solution2 {

        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = nums[1];

            if (nums.length >= 3) {
                dp[2] = Math.max(dp[1], nums[2] + dp[0]);
            }

            for (int i = 3; i < nums.length; i++) {
                dp[i] =
                    Math.max(
                        dp[i - 1],
                        Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i])
                    );
            }

            return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
        }
    }

    class Solution3 {

        public int rob(int[] nums) {
            int rob1 = 0;
            int rob2 = 0;

            for (int num : nums) {
                int max = Math.max(num + rob1, rob2);
                rob1 = rob2;
                rob2 = max;
            }

            return rob2;
        }
    }
}
