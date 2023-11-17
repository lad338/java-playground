package leetcode.dp;

public class TrappingRainWater42 {

    class Solution {

        public int trap(int[] height) {
            int[] maxLeft = new int[height.length];
            int[] maxRight = new int[height.length];
            int[] minLR = new int[height.length];

            int maxL = 0;
            int maxR = 0;
            int result = 0;
            for (int i = 0; i < height.length; i++) {
                maxLeft[i] = maxL;
                maxL = Math.max(maxL, height[i]);
                maxRight[height.length - 1 - i] = maxR;
                maxR = Math.max(maxR, height[height.length - 1 - i]);
                if (i >= height.length / 2) {
                    minLR[i] = Math.min(maxLeft[i], maxRight[i]);
                    minLR[height.length - 1 - i] =
                        Math.min(
                            maxLeft[height.length - 1 - i],
                            maxRight[height.length - 1 - i]
                        );

                    result += Math.max(0, minLR[i] - height[i]);
                    if (i != height.length - 1 - i) {
                        result +=
                            Math.max(
                                0,
                                minLR[height.length - 1 - i] -
                                height[height.length - 1 - i]
                            );
                    }
                }
            }

            return result;
        }
    }

    class O1MemSolution {

        public int trap(int[] height) {
            int result = 0;

            int maxL = height[0];
            int maxR = height[height.length - 1];
            int L = 0;
            int R = height.length - 1;
            while (L < R) {
                if (maxL <= maxR) {
                    L++;
                    result += Math.max(0, maxL - height[L]);
                    maxL = Math.max(maxL, height[L]);
                } else {
                    R--;
                    result += Math.max(0, maxR - height[R]);
                    maxR = Math.max(maxR, height[R]);
                }
            }

            return result;
        }
    }
}
