package leetcode;

import java.util.Arrays;

public class SortAnArray912 {

    class Solution {

        public int[] sortArray(int[] nums) {
            if (nums.length == 1) {
                return nums;
            }

            int L = 0;
            int R = nums.length;
            int M = (L + R) / 2;

            int[] left = sortArray(Arrays.copyOfRange(nums, L, M));
            int[] right = sortArray(Arrays.copyOfRange(nums, M, R));
            return merge(left, right);
        }

        private int[] merge(int[] a, int[] b) {
            if (a == null && b == null) return null;
            if (a == null || a.length == 0) return b;
            if (b == null || b.length == 0) return a;

            int[] result = new int[a.length + b.length];

            int i = 0;
            int j = 0;
            int index = 0;

            while (i < a.length && j < b.length) {
                if (a[i] <= b[j]) {
                    result[index] = a[i];
                    i++;
                } else {
                    result[index] = b[j];
                    j++;
                }
                index++;
            }

            while (i < a.length) {
                result[index] = a[i];
                i++;
                index++;
            }

            while (j < b.length) {
                result[index] = b[j];
                j++;
                index++;
            }

            return result;
        }
    }
}
