package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlusOne66 {

    class Solution {

        public int[] plusOne(int[] digits) {
            Deque<Integer> stack = new ArrayDeque<>();
            int overhead = 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                int current = digits[i];
                int sum = current + overhead;
                if (sum >= 10) {
                    stack.push(sum - 10);
                    overhead = 1;
                } else {
                    stack.push(sum);
                    overhead = 0;
                }
            }
            if (overhead == 1) {
                stack.push(1);
            }

            int[] results = new int[stack.size()];
            int i = 0;
            while (stack.size() > 0) {
                results[i] = stack.pop();
                i++;
            }
            return results;
        }
    }
}
