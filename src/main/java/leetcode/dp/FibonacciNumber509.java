package leetcode.dp;

public class FibonacciNumber509 {

    class Solution {

        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int[] fibs = new int[n + 1];
            fibs[0] = 0;
            fibs[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                fibs[i] = fibs[i - 1] + fibs[i - 2];
            }
            return fibs[n];
        }
    }
}
