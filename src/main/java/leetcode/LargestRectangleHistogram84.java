package leetcode;

import java.util.*;

public class LargestRectangleHistogram84 {

    class Solution {

        public class Pair {

            public int index;
            public int height;

            public Pair(int index, int height) {
                this.index = index;
                this.height = height;
            }
        }

        public int largestRectangleArea(int[] heights) {
            Deque<Pair> stack = new ArrayDeque<>();

            int max = 0;

            for (int i = 0; i < heights.length; i++) {
                int base = i;
                while (stack.size() > 0 && stack.peek().height > heights[i]) {
                    Pair popped = stack.pop();

                    max = Math.max(max, popped.height * (i - popped.index));
                    base = popped.index;
                }

                stack.push(new Pair(base, heights[i]));
            }

            while (stack.size() > 0) {
                Pair popped = stack.pop();
                max =
                    Math.max(
                        max,
                        popped.height * (heights.length - popped.index)
                    );
            }

            return max;
        }
    }
}
