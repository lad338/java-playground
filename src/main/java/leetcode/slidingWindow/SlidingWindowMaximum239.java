package leetcode.slidingWindow;

import java.util.*;
import leetcode.Pair;

public class SlidingWindowMaximum239 {

    class Solution {

        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] result = new int[nums.length - k + 1];
            ArrayDeque<Pair<Integer, Integer>> deque = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                int current = nums[i];
                while (
                    deque.size() > 0 && deque.getLast().getValue() < current
                ) {
                    deque.removeLast();
                }
                deque.addLast(new Pair<>(i, current));
            }
            result[0] = deque.getFirst().getValue();

            for (int i = 0; i + k < nums.length; i++) {
                int current = nums[i + k];
                while (deque.size() > 0 && deque.getFirst().getKey() <= i) {
                    deque.removeFirst();
                }
                while (
                    deque.size() > 0 && deque.getLast().getValue() < current
                ) {
                    deque.removeLast();
                }
                deque.addLast(new Pair<>(i + k, current));
                result[i + 1] = deque.getFirst().getValue();
            }

            return result;
        }
    }

    class NeetCodeSolution {

        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] result = new int[nums.length - k + 1];
            ArrayDeque<Integer> deque = new ArrayDeque<>(); //index
            int l = 0;

            for (int r = 0; r < nums.length; r++) {
                while (deque.size() > 0 && nums[deque.getLast()] < nums[r]) {
                    deque.removeLast();
                }
                deque.addLast(r);

                if (l > deque.getFirst()) {
                    deque.removeFirst();
                }
                if (r + 1 >= k) {
                    result[l] = nums[deque.getFirst()];
                    l++;
                }
            }
            return result;
        }
    }
}
