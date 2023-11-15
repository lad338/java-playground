package leetcode;

public class ShipWithinDays1011 {

    class Solution {

        public int shipWithinDays(int[] weights, int days) {
            int max = 0;
            int sum = 0;
            for (int weight : weights) {
                max = Math.max(max, weight);
                sum += weight;
            }

            int L = max;
            int R = sum;
            int result = sum;

            while (L <= R) {
                int M = L + (R - L) / 2;
                int index = 0;
                for (int i = 0; i < days && index < weights.length; i++) {
                    int subTotal = 0;
                    while (
                        index < weights.length && subTotal + weights[index] <= M
                    ) {
                        subTotal += weights[index];
                        index++;
                    }
                }
                if (index < weights.length) {
                    L = M + 1;
                } else {
                    result = Math.min(result, M);
                    R = M - 1;
                }
            }

            return result;
        }
    }
}
