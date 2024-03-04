package leetcode.bucketsort;

import java.util.*;

public class LeastNumberUniqueIntegersAfterKRemoval1481 {

    class BucketSortSolution {

        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int n : arr) {
                countMap.put(n, countMap.getOrDefault(n, 0) + 1);
            }

            int[] bucket = new int[arr.length + 1];

            for (int key : countMap.keySet()) {
                bucket[countMap.get(key)]++;
            }

            int result = countMap.keySet().size();

            for (int i = 1; i < bucket.length; i++) {
                int remove = bucket[i];
                if (k >= i * remove) {
                    k -= i * remove;
                    result -= remove;
                } else {
                    remove = k / i;
                    result -= remove;
                    break;
                }
            }
            return result;
        }
    }

    class HeapSolution {

        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int n : arr) {
                countMap.put(n, countMap.getOrDefault(n, 0) + 1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>();

            for (int key : countMap.keySet()) {
                heap.offer(countMap.get(key));
            }

            int result = countMap.keySet().size();

            while (!heap.isEmpty()) {
                int min = heap.poll();
                if (min <= k) {
                    k -= min;
                    result--;
                } else {
                    break;
                }
            }

            return result;
        }
    }
}
