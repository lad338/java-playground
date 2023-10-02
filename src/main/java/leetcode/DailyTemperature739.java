package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperature739 {

    class Solution {

        public int[] dailyTemperatures(int[] temperatures) {
            Deque<Integer> stack = new ArrayDeque<>();
            int[] result = new int[temperatures.length];

            for (int i = 0; i < temperatures.length; i++) {
                int temp = temperatures[i];

                while (stack.size() > 0) {
                    if (temp > temperatures[stack.peek()]) {
                        int index = stack.pop();
                        result[index] = i - index;
                    } else {
                        break;
                    }
                }
                stack.push(i);
            }

            while (stack.size() > 0) {
                int index = stack.pop();
                result[index] = 0;
            }

            return result;
        }
    }
}
