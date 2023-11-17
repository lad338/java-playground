package leetcode.greedy;

import java.util.Arrays;

public class MinimizeMaximumDifferenceInPairs2616 {

    class Solution {

        public int minimizeMax(int[] nums, int p) {
            Arrays.sort(nums);

            int result = (int) 1e9;

            int L = 0;
            int R = result;

            while (L <= R) {
                int M = L + (R - L) / 2;
                int i = 0;
                int subTotal = 0;
                while (i < nums.length - 1 && subTotal < p) {
                    if (Math.abs(nums[i] - nums[i + 1]) <= M) {
                        subTotal += 1;
                        i += 2;
                    } else {
                        i++;
                    }
                }
                if (subTotal >= p) {
                    result = M;
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }

            return result;
        }
    }
}
