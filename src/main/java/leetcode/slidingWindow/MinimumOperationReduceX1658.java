package leetcode.slidingWindow;

public class MinimumOperationReduceX1658 {

    class Solution {

        public int minOperations(int[] nums, int x) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            int required = sum - x;

            if (required < 0) {
                return -1;
            }

            if (required == 0) {
                return nums.length;
            }

            int result = 0;

            int L = 0;
            int subTotal = 0;
            for (int R = 0; R < nums.length; R++) {
                subTotal += nums[R];
                if (subTotal == required) {
                    result = Math.max(result, R - L + 1);
                } else if (subTotal > required) {
                    while (L < R && subTotal > required) {
                        subTotal -= nums[L];
                        L++;
                        if (subTotal == required) {
                            result = Math.max(result, R - L + 1);
                        }
                    }
                }
            }

            if (result == 0) {
                return -1;
            }

            return nums.length - result;
        }
    }
}
