package leetcode;

public class MaximumBinaryOddNumber2864 {

    class Solution {

        public String maximumOddBinaryNumber(String s) {
            int n = s.length();
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    count++;
                }
            }

            return "1".repeat(count - 1)
                .concat("0".repeat(n - count))
                .concat("1");
        }
    }
}
