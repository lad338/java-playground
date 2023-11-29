package leetcode.interval;

import java.util.*;

public class NonOverlappingIntervals435 {

    class Solution {

        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(it -> it[0]));

            int previousEnd = Integer.MIN_VALUE;
            int count = 0;

            for (int[] interval : intervals) {
                if (interval[0] < previousEnd) {
                    count++;
                    previousEnd = Math.min(previousEnd, interval[1]);
                    continue;
                }
                previousEnd = interval[1];
            }

            return count;
        }
    }
}
