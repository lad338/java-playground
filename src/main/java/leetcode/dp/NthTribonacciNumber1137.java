package leetcode.dp;

public class NthTribonacciNumber1137 {

    class Solution {

        public int tribonacci(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1 || n == 2) {
                return 1;
            }
            int[] tribonaccis = new int[n + 1];
            tribonaccis[0] = 0;
            tribonaccis[1] = 1;
            tribonaccis[2] = 1;
            for (int i = 3; i <= n; i++) {
                tribonaccis[i] =
                    tribonaccis[i - 1] +
                    tribonaccis[i - 2] +
                    tribonaccis[i - 3];
            }
            return tribonaccis[n];
        }
    }
}
