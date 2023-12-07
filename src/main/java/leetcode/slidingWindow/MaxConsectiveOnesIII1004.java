package leetcode.slidingWindow;

public class MaxConsectiveOnesIII1004 {

    class Solution {

        public int longestOnes(int[] nums, int k) {
            int L = 0;
            int max = 0;
            int subTotal = 0;
            for (int R = 0; R < nums.length; R++) {
                if (nums[R] == 0) {
                    max = Math.max(max, subTotal);
                    while (k == 0) {
                        subTotal--;
                        if (nums[L] == 0) {
                            k++;
                        }
                        L++;
                    }
                    k--;
                }
                subTotal++;
            }

            return Math.max(max, subTotal);
        }
    }
}
