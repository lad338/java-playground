package leetcode;

public class PowXN50 {

    class Solution {

        public double myPow(double x, int n) {
            if (n == 0) {
                return 1;
            } else if (n == 2) {
                return x * x;
            } else if (n < 0) {
                return (1 / x) * myPow(1 / x, -(n + 1));
            } else {
                if (n % 2 == 0) {
                    return myPow(myPow(x, n / 2), 2);
                } else {
                    return x * myPow(myPow(x, n / 2), 2);
                }
            }
        }
    }
}
