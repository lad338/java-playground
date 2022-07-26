package leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BuildArrayFromPermutation1920 {

    class Solution {

        public int[] buildArray(int[] nums) {
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[i] = nums[nums[i]];
            }
            return result;
        }
    }

    class OneLinerSolution {

        public int[] buildArray(int[] nums) {
            return Arrays.stream(nums).map(num -> nums[num]).toArray();
        }
    }

    class FastestSolution {

        public int[] buildArray(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[i] = nums[nums[i]];
            }
            return ans;
        }
    }
}
