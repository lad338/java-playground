package leetcode;

import java.util.*;

public class IPO502 {

    class Solution {

        public int findMaximizedCapital(
            int k,
            int w,
            int[] profits,
            int[] capital
        ) {
            PriorityQueue<Integer> capitalHeap = new PriorityQueue<>(
                Comparator.comparingInt(it -> capital[it])
            );
            PriorityQueue<Integer> profitsHeap = new PriorityQueue<>((a, b) ->
                b - a
            );

            for (int i = 0; i < capital.length; i++) {
                capitalHeap.offer(i);
            }

            while (k > 0) {
                while (
                    !capitalHeap.isEmpty() && capital[capitalHeap.peek()] <= w
                ) {
                    profitsHeap.offer(profits[capitalHeap.poll()]);
                }
                if (!profitsHeap.isEmpty()) {
                    w += profitsHeap.poll();
                    k--;
                } else {
                    break;
                }
            }
            return w;
        }
    }
}
