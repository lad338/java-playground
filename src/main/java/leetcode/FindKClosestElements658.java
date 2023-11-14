package leetcode;

import java.util.*;

public class FindKClosestElements658 {

    class Solution {

        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            PriorityQueue<Integer> results = new PriorityQueue<>();

            int index = Arrays.binarySearch(arr, x);

            int L, R;
            if (index >= 0) {
                results.offer(arr[index]);
                L = index - 1;
                R = index + 1;
                k--;
            } else {
                index = -(index + 1);
                L = index - 1;
                R = index;
            }

            for (int i = k; i > 0; i--) {
                if (L < 0) {
                    results.offer(arr[R]);
                    R++;
                    continue;
                }

                if (R >= arr.length) {
                    results.offer(arr[L]);
                    L--;
                    continue;
                }

                int absL = Math.abs(arr[L] - x);
                int absR = Math.abs(arr[R] - x);

                if (absL < absR) {
                    results.offer(arr[L]);
                    L--;
                } else if (absR < absL) {
                    results.offer(arr[R]);
                    R++;
                } else {
                    if (arr[R] < arr[L]) {
                        results.offer(arr[R]);
                        R++;
                    } else {
                        results.offer(arr[L]);
                        L--;
                    }
                }
            }

            List<Integer> result = new ArrayList<>();
            while (!results.isEmpty()) {
                result.add(results.poll());
            }
            return result;
        }
    }
}
