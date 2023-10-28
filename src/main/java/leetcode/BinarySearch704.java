package leetcode;

import java.util.*;

public class BinarySearch704 {

    class Solution {

        public int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;

            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[m] < target) {
                    l = m + 1;
                } else if (nums[m] > target) {
                    r = m - 1;
                } else {
                    return m;
                }
            }

            return -1;
        }
    }

    class BuiltInSolution {

        public int search(int[] nums, int target) {
            return Math.max(-1, Arrays.binarySearch(nums, target));
        }
    }
}
