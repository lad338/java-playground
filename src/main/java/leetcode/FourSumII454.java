package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FourSumII454 {

    class Solution {

        public int fourSumCount(
            int[] nums1,
            int[] nums2,
            int[] nums3,
            int[] nums4
        ) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num1 : nums1) {
                for (int num2 : nums2) {
                    int sum = num1 + num2;
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }

            int count = 0;
            for (int num3 : nums3) {
                for (int num4 : nums4) {
                    count += map.getOrDefault(-num3 - num4, 0);
                }
            }

            return count;
        }
    }

    class JavaOptimisedSolution {

        class Solution {

            public int fourSumCount(
                int[] nums1,
                int[] nums2,
                int[] nums3,
                int[] nums4
            ) {
                int[] minMax1 = findMinMax(nums1);
                int[] minMax2 = findMinMax(nums2);
                int[] minMax3 = findMinMax(nums3);
                int[] minMax4 = findMinMax(nums4);

                int min = Math.min(
                    minMax1[0] + minMax2[0],
                    -minMax3[1] - minMax4[1]
                );
                int max = Math.max(
                    minMax1[1] + minMax2[1],
                    -minMax3[0] - minMax4[0]
                );
                int[] map = new int[max - min + 1];

                for (int num1 : nums1) {
                    for (int num2 : nums2) {
                        map[num1 + num2 - min]++;
                    }
                }

                int result = 0;
                for (int num3 : nums3) {
                    for (int num4 : nums4) {
                        result += map[-num3 - num4 - min];
                    }
                }
                return result;
            }

            private int[] findMinMax(int[] nums) {
                int[] result = new int[2];
                result[0] = nums[0];
                result[1] = nums[0];

                for (int num : nums) {
                    result[0] = Math.min(result[0], num);
                    result[1] = Math.max(result[1], num);
                }
                return result;
            }
        }
    }
}
