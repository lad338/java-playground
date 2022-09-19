package leetcode;

public class CountingBits338 {

    class Solution {

        public int[] countBits(int n) {
            int[] results = new int[n + 1];
            results[0] = 0;
            int digit = 1;
            int copyIndex = 0;
            for (int i = 1; i <= n; i++) {
                if (i >= Math.pow(2, digit)) {
                    digit++;
                    copyIndex = 0;
                }
                results[i] = results[copyIndex] + 1;
                copyIndex++;
            }
            return results;
        }
    }

    class NoBuiltInFunctionSolution {

        public int[] countBits(int n) {
            int[] results = new int[n + 1];
            results[0] = 0;
            int limit = 2;
            int copyIndex = 0;
            for (int i = 1; i <= n; i++) {
                if (i >= limit) {
                    limit *= 2;
                    copyIndex = 0;
                }
                results[i] = results[copyIndex] + 1;
                copyIndex++;
            }
            return results;
        }
    }
}
