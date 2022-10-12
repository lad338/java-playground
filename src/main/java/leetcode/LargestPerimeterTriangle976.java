package leetcode;

import java.util.Arrays;

public class LargestPerimeterTriangle976 {

    public class Solution {

        public int largestPerimeter(int[] nums) {
            Arrays.sort(nums);
            for (int i = 2; i < nums.length; i++) if (
                nums[nums.length - 1 - i] +
                nums[nums.length - 1 - i + 1] >
                nums[nums.length - 1 - i + 2]
            ) return (
                nums[nums.length - 1 - i] +
                nums[nums.length - 1 - i + 1] +
                nums[nums.length - 1 - i + 2]
            );
            return 0;
        }
    }
}
