package leetcode;

import java.util.*;

public class TaskScheduler621 {

    class Solution {

        public int leastInterval(char[] tasks, int n) {
            int[] countMap = new int[26];
            for (char c : tasks) {
                countMap[c - 'A'] += 1;
            }

            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                b[0] - a[0]
            );

            for (int count : countMap) {
                if (count == 0) {
                    continue;
                }
                maxHeap.offer(new int[] { count, 0 });
            }

            Deque<int[]> queue = new ArrayDeque<>();

            int result = 0;
            while (!maxHeap.isEmpty() || !queue.isEmpty()) {
                while (!queue.isEmpty() && queue.peek()[1] < result) {
                    maxHeap.offer(queue.poll());
                }
                if (!maxHeap.isEmpty()) {
                    int[] first = maxHeap.poll();
                    if (first[0] > 1) {
                        first[0] -= 1;
                        first[1] = result + n;
                        queue.offer(first);
                    }
                }
                result++;
            }
            return result;
        }
    }

    class SlightOptimizedSolution {

        public int leastInterval(char[] tasks, int n) {
            int[] countMap = new int[26];
            for (char c : tasks) {
                countMap[c - 'A'] += 1;
            }

            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                b[0] - a[0]
            );

            for (int count : countMap) {
                if (count == 0) {
                    continue;
                }
                maxHeap.offer(new int[] { count, 0 });
            }

            Deque<int[]> queue = new ArrayDeque<int[]>();

            int result = 0;
            while (!maxHeap.isEmpty() || !queue.isEmpty()) {
                while (!queue.isEmpty() && queue.peek()[1] < result) {
                    maxHeap.offer(queue.poll());
                }
                if (!maxHeap.isEmpty()) {
                    int[] first = maxHeap.poll();
                    if (first[0] > 1) {
                        first[0] -= 1;
                        first[1] = result + n;
                        queue.offer(first);
                    }
                }
                if (maxHeap.size() == 0 && !queue.isEmpty()) {
                    result = queue.peek()[1] + 1;
                } else {
                    result++;
                }
            }
            return result;
        }
    }
}
