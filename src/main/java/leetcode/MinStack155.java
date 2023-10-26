package leetcode;

import java.util.*;

public class MinStack155 {

    class MinStack {

        Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();

        public MinStack() {}

        public void push(int val) {
            if (stack.size() == 0) {
                stack.push(new Pair<>(val, val));
            } else {
                stack.push(
                    new Pair<>(val, Math.min(val, stack.peek().getValue()))
                );
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().getKey();
        }

        public int getMin() {
            return stack.peek().getValue();
        }
    }
}
