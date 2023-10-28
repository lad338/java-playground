package leetcode;

public class Sqrt69 {

    class Solution {

        public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            }

            int l = 1;
            int r = x;
            int result = 1;
            while (l <= r) {
                long m = (l + (long) r) / 2;
                if (m * m < x) {
                    result = Math.max((int) m, result);
                    l = (int) m + 1;
                } else if (m * m > x) {
                    r = (int) m - 1;
                } else {
                    return (int) m;
                }
            }
            return result;
        }
    }
}
