package leetcode.interval;

import java.util.*;

public class InsertInterval57 {

    class Solution {

        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals.length == 0) {
                return new int[][] { newInterval };
            }

            List<int[]> result = new ArrayList<>();
            boolean isMerging = newInterval[0] < intervals[0][0];
            boolean isDone = false;

            for (int[] interval : intervals) {
                if (!isDone && newInterval[1] < interval[0]) {
                    result.add(newInterval);
                    result.add(interval);
                    isMerging = false;
                    isDone = true;
                    continue;
                }
                if (isDone) {
                    result.add(interval);
                    continue;
                }

                if (
                    interval[0] > newInterval[1] || newInterval[0] > interval[1]
                ) {
                    if (isMerging) {
                        result.add(newInterval);
                    }
                    isMerging = false;
                    result.add(interval);
                    continue;
                }
                isMerging = true;
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
            if (isMerging || !isDone) {
                result.add(newInterval);
            }

            int[][] returnResult = new int[result.size()][2];
            for (int i = 0; i < result.size(); i++) {
                returnResult[i] = result.get(i);
            }
            return returnResult;
        }
    }

    class NeetcodeSolution {

        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (intervals.length == 0) {
                return new int[][] { newInterval };
            }

            List<int[]> result = new ArrayList<>();
            boolean isDone = false;
            for (int i = 0; i < intervals.length; i++) {
                int[] interval = intervals[i];

                if (interval[0] > newInterval[1]) {
                    result.add(newInterval);
                    for (int j = i; j < intervals.length; j++) {
                        result.add(intervals[j]);
                    }
                    isDone = true;
                    break;
                }

                if (interval[1] < newInterval[0]) {
                    result.add(interval);
                } else {
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                }
            }
            if (!isDone) {
                result.add(newInterval);
            }

            int[][] returnResult = new int[result.size()][2];
            for (int i = 0; i < result.size(); i++) {
                returnResult[i] = result.get(i);
            }
            return returnResult;
        }
    }
}
