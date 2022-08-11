package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReverseInteger7 {

    static class Solution {

        public int reverse(int x) {
            if (Integer.MIN_VALUE == x) {
                return 0;
            }
            boolean isDone = false;
            List<Integer> digits = new ArrayList<>();
            boolean isPositive = x >= 0;
            if (!isPositive) {
                x = -x;
            }

            int length = (int) (Math.log10(x) + 1);
            for (int i = 0; i < length; i++) {
                int digit = x / ((int) Math.pow(10, i)) % 10;
                digits.add(digit);
            }

            int result = 0;
            boolean hasNonZero = false;

            try {
                for (int i = 0; i < digits.size(); i++) {
                    int current = digits.get(i);
                    if (current != 0) {
                        result =
                            Math.addExact(
                                result,
                                (isPositive ? 1 : -1) *
                                Math.multiplyExact(
                                    current,
                                    (int) (Math.pow(10, digits.size() - i - 1))
                                )
                            );
                        if (i == 0) {
                            if (isPositive && result < 0) {
                                return 0;
                            } else if (!isPositive && result > 0) {
                                return 0;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                return 0;
            }

            return result;
        }
    }
}
