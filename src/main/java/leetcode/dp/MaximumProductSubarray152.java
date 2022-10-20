package leetcode.dp;

public class MaximumProductSubarray152 {

    class Solution {

        public int maxProduct(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            int product1 = nums[0];
            int product2 = nums[0];
            int max = nums[0];

            for (int i = 1; i < nums.length; i++) {
                int current = nums[i];
                if (current < 0) {
                    if (product1 == 0) {
                        product1 = current;
                        product2 = current;
                    } else if (product1 < 0 && product2 < 0) {
                        product1 =
                            Math.max(product1 * current, product2 * current);
                        product2 = current;
                    } else if (product1 > 0 && product2 > 0) {
                        max = Math.max(product1, max);
                        product2 *= current;
                        product1 *= current;
                    } else if (product1 > 0) {
                        max = Math.max(product1, max);
                        int temp = product1;
                        product1 = product2 * current;
                        product2 = temp * current;
                    }
                }
                if (current > 0) {
                    if (product1 <= 0) {
                        product1 = current;
                    } else {
                        product1 *= current;
                    }

                    if (product2 == 0) {
                        product2 = current;
                    } else {
                        product2 *= current;
                    }
                }
                if (current == 0) {
                    max =
                        Math.max(
                            0,
                            Math.max(max, Math.max(product1, product2))
                        );
                    product1 = 0;
                    product2 = 0;
                }
            }
            return Math.max(max, Math.max(product1, product2));
        }
    }

    class PrefixSuffixSolution {

        public int maxProduct(int[] nums) {
            int l = nums[0];
            int r = nums[nums.length - 1];
            int max = Math.max(l, r);

            for (int i = 1; i < nums.length; i++) {
                l = (l == 0 ? 1 : l) * nums[i];
                r = (r == 0 ? 1 : r) * nums[nums.length - 1 - i];
                max = Math.max(max, Math.max(l, r));
            }
            return max;
        }
    }
}
