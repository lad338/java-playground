package leetcode;

public class KthSymbolInGrammar779 {

    class Solution {

        public int kthGrammar(int n, int k) {
            int result = 0;
            int left = 1;
            int right = (int) Math.pow(2, n - 1);

            for (int i = 0; i < n - 1; i++) {
                int mid = (left + right) / 2;
                if (k <= mid) {
                    right = mid;
                } else {
                    left = mid + 1;
                    result = result == 1 ? 0 : 1;
                }
            }
            return result;
        }
    }
}
