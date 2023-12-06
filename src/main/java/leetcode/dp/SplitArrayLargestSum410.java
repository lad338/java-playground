package leetcode.dp;

public class SplitArrayLargestSum410 {

    class BinarySearchSolution {

        int[] nums;
        int k;

        public int splitArray(int[] nums, int k) {
            this.nums = nums;
            this.k = k;

            int L = 0;
            int R = 0;
            for (int num : nums) {
                L = Math.max(L, num);
                R += num;
            }

            int result = R;
            while (L <= R) {
                int M = L + (R - L) / 2;
                if (canSplit(M)) {
                    result = M;
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }
            return result;
        }

        private boolean canSplit(int max) {
            int count = 1;
            int subTotal = 0;
            for (int num : nums) {
                subTotal += num;
                if (subTotal > max) {
                    count++;
                    subTotal = num;
                }
            }
            return count <= k;
        }
    }
}
