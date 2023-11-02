package leetcode.slidingWindow;

import java.util.*;
import java.util.stream.Collectors;

public class MinOperationsMakeArrayContinous2009 {

    class Solution {

        public int minOperations(int[] nums) {
            int result = nums.length;

            final List<Integer> sorted = Arrays
                .stream(nums)
                .boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

            int r = 0;
            for (int l = 0; l < sorted.size(); l++) {
                while (
                    r < sorted.size() &&
                    sorted.get(r) < sorted.get(l) + nums.length
                ) {
                    r++;
                }
                result = Math.min(result, nums.length - (r - l));
            }
            return result;
        }
    }

    class PrimitiveSolution {

        public int minOperations(int[] nums) {
            int result = nums.length;

            Arrays.sort(nums);

            int r = 0;
            int duplicates = 0;
            for (int l = 0; l < nums.length; l++) {
                if (l > 0 && nums[l] == nums[l - 1]) {
                    duplicates--;
                }

                while (r < nums.length && nums[r] < nums[l] + nums.length) {
                    r++;
                    if (r > 0 && r < nums.length && nums[r] == nums[r - 1]) {
                        duplicates++;
                    }
                }
                result = Math.min(result, nums.length - (r - l - duplicates));
            }
            return result;
        }
    }
}
