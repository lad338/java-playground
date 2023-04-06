package leetcode;

public class ContainerWithMostWater11 {

    class Solution {

        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = Math.min(height[left], height[right]) * (right - left);

            while (left < right) {
                if (height[left] > height[right]) {
                    right--;
                } else {
                    left++;
                }
                max =
                    Math.max(
                        Math.min(height[left], height[right]) * (right - left),
                        max
                    );
            }

            return max;
        }
    }
}
