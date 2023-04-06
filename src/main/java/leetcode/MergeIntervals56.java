package leetcode;

import java.util.*;
import java.util.Collections.*;

public class MergeIntervals56 {

    class InitSolution {

        public int[][] merge(int[][] intervals) {
            List<List<Integer>> intervalsList = new ArrayList<>();
            List<List<Integer>> results = new ArrayList<>();

            for (int[] in : intervals) {
                intervalsList.add(List.of(in[0], in[1]));
            }

            intervalsList.sort(Comparator.comparing(it -> it.get(0)));

            Deque<List<Integer>> intervalDeque = new ArrayDeque<>(
                intervalsList
            );

            while (intervalDeque.size() > 1) {
                List<Integer> a = intervalDeque.poll();
                List<Integer> b = intervalDeque.poll();

                if (a.get(1) >= b.get(0)) {
                    intervalDeque.push(
                        List.of(a.get(0), Math.max(a.get(1), b.get(1)))
                    );
                } else {
                    results.add(a);
                    intervalDeque.push(b);
                }
            }
            results.add(intervalDeque.pop());

            int[][] r = new int[results.size()][2];

            for (int i = 0; i < results.size(); i++) {
                r[i][0] = results.get(i).get(0);
                r[i][1] = results.get(i).get(1);
            }
            return r;
        }
    }

    class BetterMemSolution {

        class Solution {

            public int[][] merge(int[][] intervals) {
                if (intervals.length == 1) {
                    return intervals;
                }

                List<Integer> start = new ArrayList<>();
                List<Integer> end = new ArrayList<>();

                for (int[] in : intervals) {
                    start.add(in[0]);
                    end.add(in[1]);
                }

                Collections.sort(start);
                Collections.sort(end);

                int p1 = 0;
                int p2 = 0;
                List<int[]> results = new ArrayList<>();

                while (p2 < end.size()) {
                    if (p2 == end.size() - 1) {
                        results.add(new int[] { start.get(p1), end.get(p2) });
                    } else {
                        if (end.get(p2) < start.get(p2 + 1)) {
                            results.add(
                                new int[] { start.get(p1), end.get(p2) }
                            );
                            p1 = p2 + 1;
                        }
                    }
                    p2++;
                }

                return results.toArray(new int[results.size()][2]);
            }
        }
    }
}
