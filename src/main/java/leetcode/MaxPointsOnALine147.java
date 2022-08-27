package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine147 {

    class Solution {

        public int maxPoints(int[][] points) {
            // slopeMap = HashMap<Decimal, HashSet<Integer>>
            // for all point in points
            //   for all point in points later in the list
            //     find the line y = mx + C
            //     special handling for vertical line
            //     put the points in set using Pair<m, C> as key and point index as Value in set
            // return max size for all values for all in map

            if (points.length == 0) {
                return 0;
            }
            int max = 1;

            Map<Double, Integer> slopeMap = new HashMap<>();

            for (int i = 0; i < points.length - 1; i++) {
                slopeMap.clear();
                int verticalCount = 1;
                int baseX = points[i][0];
                int baseY = points[i][1];
                for (int j = i + 1; j < points.length; j++) {
                    int compareX = points[j][0];
                    int compareY = points[j][1];

                    if (compareX == baseX) {
                        verticalCount++;
                    } else {
                        Double slope = compareY == baseY
                            ? 0
                            : ((compareY - baseY) * 1.0) / (compareX - baseX);

                        slopeMap.put(
                            slope,
                            slopeMap.getOrDefault(slope, 1) + 1
                        );
                    }
                }
                for (int count : slopeMap.values()) {
                    max = Math.max(max, count);
                }
                max = Math.max(max, verticalCount);
            }
            return max;
        }
    }
}
