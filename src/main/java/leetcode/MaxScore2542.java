package leetcode;

import java.util.*;

public class MaxScore2542 {

    class Solution {

        public long maxScore(int[] nums1, int[] nums2, int k) {
            int[][] nums = new int[nums1.length][2];
            for (int i = 0; i < nums1.length; i++) {
                nums[i][0] = nums2[i];
                nums[i][1] = nums1[i];
            }

            Arrays.sort(nums, (a, b) -> b[0] - a[0]);

            long result = Integer.MIN_VALUE;
            long subTotal = 0;
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

            for (int[] num : nums) {
                subTotal += num[1];
                minHeap.offer(num[1]);
                if (minHeap.size() == k) {
                    result = Math.max(result, num[0] * subTotal);
                    subTotal -= minHeap.poll();
                }
            }

            return result;
        }
    }
}
