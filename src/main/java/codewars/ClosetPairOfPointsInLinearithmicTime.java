package codewars;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ClosetPairOfPointsInLinearithmicTime {

    public class Kata {

        public static List<Point> closestPair(List<Point> points) {
            return recursiveCall(points, true);
        }

        public static List<Point> recursiveCall(
            List<Point> points,
            boolean isYAxisCut
        ) {
            if (points.size() > 2) {
                List<Double> minMaxXY = findMinMaxXY(points);

                double cut =
                    (
                        isYAxisCut
                            ? (minMaxXY.get(0) + minMaxXY.get(1)) / 2
                            : (minMaxXY.get(2) + minMaxXY.get(3)) / 2
                    );

                List<Point> lhsPoints = new ArrayList<>();

                List<Point> rhsPoints = new ArrayList<>();

                for (Point point : points) {
                    if ((isYAxisCut ? point.x : point.y) <= cut) {
                        lhsPoints.add(point);
                    } else {
                        rhsPoints.add(point);
                    }
                }

                List<Point> lhsResult = lhsPoints.size() > 2
                    ? recursiveCall(lhsPoints, !isYAxisCut)
                    : lhsPoints;

                double lhsDistance = subDistance(lhsResult, isYAxisCut, cut);

                List<Point> rhsResult = rhsPoints.size() > 2
                    ? recursiveCall(rhsPoints, !isYAxisCut)
                    : rhsPoints;

                double rhsDistance = subDistance(rhsResult, isYAxisCut, cut);

                if (lhsPoints.size() == 0) {
                    return rhsResult;
                }
                if (rhsPoints.size() == 0) {
                    return lhsResult;
                }

                double minDistance = Math.min(lhsDistance, rhsDistance);

                List<Point> filteredPoints = points
                    .stream()
                    .filter(it ->
                        Math.abs((isYAxisCut ? it.x : it.y) - cut) <
                        minDistance &&
                        !lhsResult.contains(it) &&
                        !rhsResult.contains(it)
                    )
                    .collect(Collectors.toList());

                filteredPoints.addAll(lhsResult);
                filteredPoints.addAll(rhsResult);

                return nSquaredSearch(filteredPoints.stream().toList());
            }
            return points;
        }

        public static double subDistance(
            List<Point> result,
            boolean isYAxisCut,
            double cut
        ) {
            return result.size() == 0
                ? 0
                : result.size() == 1
                    ? Math.abs(
                        isYAxisCut
                            ? result.get(0).x - cut
                            : result.get(0).y - cut
                    )
                    : distanceFormula(result.get(0), result.get(1));
        }

        public static Double distanceFormula(Point a, Point b) {
            return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        }

        public static List<Point> nSquaredSearch(List<Point> points) {
            double minDistance = Integer.MAX_VALUE;
            int a = 0;
            int b = 1;

            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    double distance = distanceFormula(
                        points.get(i),
                        points.get(j)
                    );
                    if (distance < minDistance) {
                        minDistance = distance;
                        a = i;
                        b = j;
                    }
                }
            }

            return Arrays.asList(points.get(a), points.get(b));
        }

        public static List<Double> findMinMaxXY(List<Point> points) {
            double minX = points.get(0).x;
            double maxX = points.get(0).x;
            double minY = points.get(0).y;
            double maxY = points.get(0).y;

            for (Point point : points) {
                minX = Math.min(minX, point.x);
                maxX = Math.max(maxX, point.x);
                minY = Math.min(minY, point.y);
                maxY = Math.max(maxY, point.y);
            }

            return Arrays.asList(minX, maxX, minY, maxY);
        }
    }
}
