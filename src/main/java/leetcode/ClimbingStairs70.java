package leetcode;

public class ClimbingStairs70 {

    class Solution {

        int[] steps = new int[45];

        public int climbStairs(int n) {
            steps[0] = 1;
            steps[1] = 2;
            steps[2] = 3;
            for (int i = 3; i < n; i++) {
                steps[i] = 2 * steps[i - 2] + steps[i - 3];
            }
            return steps[n - 1];
        }
    }

    class RecursiveSolution {

        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }

            if (n == 2) {
                return 2;
            }

            if (n == 3) {
                return 3;
            }

            return 2 * climbStairs(n - 2) + climbStairs(n - 3);
        }
    }
}
