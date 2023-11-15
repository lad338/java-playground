package leetcode.greedy;

import java.util.Arrays;

public class MaximumElementAfterDecrementingAndRearranging1846 {

    class Solution {

        public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
            Arrays.sort(arr);

            int prev = 0;
            for (int num : arr) {
                prev = Math.min(prev + 1, num);
            }
            return prev;
        }
    }
}
