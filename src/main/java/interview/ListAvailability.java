package interview;

import java.util.*;
import java.util.stream.Stream;

public class ListAvailability {

    public static class Interval {

        @Override
        public String toString() {
            return "Interval{" + "start=" + start + ", end=" + end + '}';
        }

        public Integer start;
        public Integer end;

        public Interval(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class Availability {

        public Availability(Interval interval, String name) {
            this.interval = interval;
            this.name = name;
        }

        public Interval interval;
        public String name;
    }

    public static Map<Interval, Set<String>> function(
        List<Availability> availabilities
    ) {
        Map<Interval, Set<String>> result = new HashMap<>();

        final List<Integer> intervalEndPoints = availabilities
            .stream()
            .flatMap(it -> Stream.of(it.interval.start, it.interval.end))
            .distinct()
            .sorted()
            .toList();

        List<Interval> resultIntervals = new ArrayList<>();

        int ref = intervalEndPoints.get(0);
        for (int i = 1; i < intervalEndPoints.size(); i++) {
            int current = intervalEndPoints.get(i);

            final Interval interval = new Interval(ref, current);
            resultIntervals.add(interval);
            ref = current;
        }

        List<Integer> intervalStarts = resultIntervals
            .stream()
            .map(it -> it.start)
            .toList();

        for (Availability availability : availabilities) {
            final int index = Collections.binarySearch(
                intervalStarts,
                availability.interval.start
            );
            for (
                int i = index;
                i < resultIntervals.size() &&
                resultIntervals.get(i).end <= availability.interval.end;
                i++
            ) {
                final Set<String> set = result.getOrDefault(
                    resultIntervals.get(i),
                    new HashSet<>()
                );

                set.add(availability.name);

                result.put(resultIntervals.get(i), set);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final Map<Interval, Set<String>> result = function(
            List.of(
                new Availability(new Interval(10, 100), "Abby"),
                new Availability(new Interval(20, 40), "Ben"),
                new Availability(new Interval(60, 70), "Clare"),
                new Availability(new Interval(120, 180), "Don"),
                new Availability(new Interval(30, 150), "Eddie")
            )
        );

        System.out.println(result);
    }
}
