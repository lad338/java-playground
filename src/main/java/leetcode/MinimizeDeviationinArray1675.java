package leetcode;

import java.util.*;

public class MinimizeDeviationinArray1675 {

    class NeetcodeSolution {

        // N LogN LogM
        public int minimumDeviation(int[] nums) {
            Set<Integer> duplicates = new HashSet<>();
            PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>(
                Comparator.comparingInt(Pair::getKey)
            );
            int max = Integer.MIN_VALUE;

            for (int num : nums) {
                if (duplicates.contains(num)) {
                    continue;
                }

                duplicates.add(num);

                int n = num;
                while (n % 2 == 0) {
                    n /= 2;
                }
                max = Math.max(max, n);
                heap.offer(new Pair<>(n, Math.max(num, 2 * n)));
            }

            int result = Integer.MAX_VALUE;
            int heapCount = heap.size();

            while (heap.size() == heapCount) {
                Pair<Integer, Integer> pair = heap.poll();
                result = Math.min(result, Math.abs(max - pair.getKey()));
                if (pair.getKey() < pair.getValue()) {
                    heap.offer(new Pair<>(2 * pair.getKey(), pair.getValue()));
                    max = Math.max(max, 2 * pair.getKey());
                }
            }

            return result;
        }
    }

    class Solution {

        public int minimumDeviation(int[] arr) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            Set<Integer> duplicates = new HashSet<>();

            for (int num : arr) {
                if (duplicates.contains(num)) {
                    continue;
                }
                duplicates.add(num);
                int n = num % 2 == 0 ? num : num * 2;
                min = Math.min(min, n);
                max = Math.max(max, n);
                pq.offer(n);
            }

            int diff = max - min;
            while (!pq.isEmpty() && max % 2 == 0) {
                int currentMax = pq.poll();
                diff = Math.min(currentMax - min, diff);
                min = Math.min(currentMax / 2, min);
                pq.offer(currentMax / 2);
                max = currentMax;
            }
            return diff;
        }
    }
}
