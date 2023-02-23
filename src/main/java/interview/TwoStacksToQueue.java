package interview;

import java.util.ArrayDeque;
import java.util.Deque;

// Implement a first in first out (FIFO) queue using only two stacks.
// The implemented queue should support all the functions of a normal
// queue (push, peek, pop, and empty).

// Implement the MyQueue class:
// void push(int x) Pushes element x to the back of the queue.
// int pop() Removes the element from the front of the queue and returns it.
// int peek() Returns the element at the front of the queue.
// boolean empty() Returns true if the queue is empty, false otherwise.
// Notes:

// You must use only standard operations of a stack, which means only push to top,
// peek/pop from top, size, and is empty operations are valid.
// Depending on your language, the stack may not be supported natively.
// You may simulate a stack using a list or deque (double-ended queue)
// as long as you use only a stack's standard operations.

public class TwoStacksToQueue {

    public interface MyQueue {
        void push(int n);
        int pop();
        int peek();
        boolean empty();
    }

    static class MyQueueInterview implements MyQueue {

        private final Deque<Integer> stack1;
        private final Deque<Integer> stack2;

        public MyQueueInterview() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (stack1.size() == 0) {
                return 0;
            }
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            int result = stack1.pop();
            while (stack2.size() > 0) {
                stack1.push(stack2.pop());
            }
            return result;
        }

        public int peek() {
            if (stack1.size() == 0) {
                return 0;
            }
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            int result = stack1.peek();
            while (stack2.size() > 0) {
                stack1.push(stack2.pop());
            }
            return result;
        }

        public boolean empty() {
            return stack1.size() == 0;
        }
    }

    static <T> void popNToStack(
        Deque<T> original,
        Deque<T> destination,
        int remaining
    ) {
        while (original.size() > remaining) {
            destination.push(original.pop());
        }
    }

    static class MyQueueOptimized implements MyQueue {

        private final Deque<Integer> stack1;
        private final Deque<Integer> stack2;
        private boolean isStackOrder = true;

        public MyQueueOptimized() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void push(int x) {
            if (!isStackOrder) {
                popNToStack(stack2, stack1, 0);
                isStackOrder = true;
            }
            stack1.push(x);
        }

        public int pop() {
            if (isStackOrder) {
                popNToStack(stack1, stack2, 1);
                isStackOrder = false;
                return stack1.pop();
            }
            return stack2.pop();
        }

        public int peek() {
            if (isStackOrder) {
                popNToStack(stack1, stack2, 0);
                isStackOrder = false;
            }
            return stack2.peek();
        }

        public boolean empty() {
            if (isStackOrder) {
                return stack1.isEmpty();
            }
            return stack2.isEmpty();
        }
    }

    public static class Main {

        public static void main(String[] args) {
            MyQueue myQueue = new MyQueueInterview();
            myQueue.push(1); // queue is: [1]
            myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
            myQueue.peek(); // return 1
            myQueue.pop(); // return 1, queue is [2]
            myQueue.empty(); // return false
        }
    }
}
