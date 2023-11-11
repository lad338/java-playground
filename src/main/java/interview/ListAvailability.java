package interview;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import leetcode.Pair;

public class ListAvailability {

    public static class Interval {

        @Override
        public String toString() {
            return "Interval{" + start + "," + end + '}';
        }

        public Integer start;
        public Integer end;

        public Interval(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public static Interval of(Integer start, Integer end) {
            return new Interval(start, end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Interval interval)) return false;
            return (
                Objects.equals(start, interval.start) &&
                Objects.equals(end, interval.end)
            );
        }
    }

    public static class Availability {

        public Availability(Interval interval, String name) {
            this.interval = interval;
            this.name = name;
        }

        public Interval interval;
        public String name;

        @Override
        public String toString() {
            return ("Availability{" + interval + "," + name + '}');
        }
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

    public static Map<Interval, Set<String>> functionNLogN(
        List<Availability> availabilities
    ) {
        Map<Interval, Set<String>> result = new HashMap<>();

        List<Availability> copied = new ArrayList<>(availabilities);
        copied.sort(Comparator.comparingInt(it -> it.interval.start));

        PriorityQueue<Pair<String, Integer>> heap = new PriorityQueue<>(
            Comparator.comparingInt(Pair::getValue)
        );

        int startTime = copied.get(0).interval.start;

        for (Availability current : copied) {
            if (!current.interval.start.equals(startTime)) {
                int endTime = current.interval.start;
                while (heap.size() > 0 && heap.peek().getValue() < endTime) {
                    final Pair<String, Integer> first = heap.peek();
                    if (startTime < first.getValue()) {
                        Set<String> names = result.getOrDefault(
                            Interval.of(startTime, first.getValue()),
                            new HashSet<>()
                        );
                        names.addAll(heap.stream().map(Pair::getKey).toList());
                        result.put(
                            Interval.of(startTime, first.getValue()),
                            names
                        );
                        startTime = first.getValue();
                    }
                    heap.poll();
                }

                if (heap.size() > 0) {
                    Set<String> names = result.getOrDefault(
                        Interval.of(startTime, endTime),
                        new HashSet<>()
                    );
                    names.addAll(heap.stream().map(Pair::getKey).toList());
                    result.put(Interval.of(startTime, endTime), names);
                }
                startTime = endTime;
            }
            heap.add(new Pair<>(current.name, current.interval.end));
        }

        while (heap.size() > 0) {
            final Pair<String, Integer> first = heap.peek();
            if (startTime < first.getValue()) {
                Set<String> names = result.getOrDefault(
                    Interval.of(startTime, first.getValue()),
                    new HashSet<>()
                );
                names.addAll(heap.stream().map(Pair::getKey).toList());
                result.put(Interval.of(startTime, first.getValue()), names);
            }
            startTime = heap.poll().getValue();
        }

        return result;
    }

    public static Map<Interval, Set<String>> functionNLogNCleaner(
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

        for (int i = 0; i < intervalEndPoints.size() - 1; i++) {
            final Interval interval = Interval.of(
                intervalEndPoints.get(i),
                intervalEndPoints.get(i + 1)
            );
            resultIntervals.add(interval);
        }

        List<Availability> copied = new ArrayList<>(availabilities);
        copied.sort(Comparator.comparingInt(it -> it.interval.start));
        PriorityQueue<Pair<String, Integer>> heap = new PriorityQueue<>(
            Comparator.comparingInt(Pair::getValue)
        );

        int personIndex = 0;
        for (Interval interval : resultIntervals) {
            while (
                personIndex < copied.size() &&
                copied.get(personIndex).interval.start.equals(interval.start)
            ) {
                heap.add(
                    new Pair<>(
                        copied.get(personIndex).name,
                        copied.get(personIndex).interval.end
                    )
                );
                personIndex++;
            }

            while (
                heap.size() > 0 && heap.peek().getValue() <= interval.start
            ) {
                heap.poll();
            }

            if (heap.size() > 0) {
                result.put(
                    interval,
                    new HashSet<>(heap.stream().map(Pair::getKey).toList())
                );
            }
        }

        return result;
    }

    public static Map<Interval, Set<String>> nLogNShort(
        List<Availability> availabilities
    ) {
        Map<Interval, Set<String>> result = new HashMap<>();
        PriorityQueue<Availability> heap = new PriorityQueue<>(
            Comparator.comparingInt(it -> it.interval.end)
        );

        availabilities =
            availabilities
                .stream()
                .sorted(Comparator.comparingInt(it -> it.interval.start))
                .toList();

        final List<Integer> ends = availabilities
            .stream()
            .flatMap(it -> Stream.of(it.interval.start, it.interval.end))
            .distinct()
            .sorted()
            .toList();
        final List<Interval> resultIntervals = new ArrayList<>();
        for (int i = 0; i < ends.size() - 1; i++) {
            resultIntervals.add(Interval.of(ends.get(i), ends.get(i + 1)));
        }

        int availabilityIndex = 0;
        for (Interval interval : resultIntervals) {
            while (
                availabilityIndex < availabilities.size() &&
                availabilities
                    .get(availabilityIndex)
                    .interval.start.equals(interval.start)
            ) {
                heap.add(availabilities.get(availabilityIndex));
                availabilityIndex++;
            }
            while (
                !heap.isEmpty() && heap.peek().interval.end <= interval.start
            ) {
                heap.poll();
            }

            if (!heap.isEmpty()) {
                Set<String> names = heap
                    .stream()
                    .map(it -> it.name)
                    .collect(Collectors.toSet());
                result.put(interval, names);
            }
        }

        return result;
    }

    private static void print(Map<Interval, Set<String>> result) {
        PriorityQueue<Pair<Interval, Set<String>>> heap = new PriorityQueue<>(
            (a, b) -> {
                if (a.getKey().start.equals(b.getKey().end)) {
                    return a.getKey().end.compareTo(b.getKey().end);
                }
                return a.getKey().start.compareTo(b.getKey().start);
            }
        );
        result.forEach((key, value) -> heap.add(new Pair<>(key, value)));
        StringBuilder sb = new StringBuilder();
        while (heap.size() > 0) {
            final Pair<Interval, Set<String>> poll = heap.poll();
            sb.append(poll.getKey());
            sb.append("=");
            sb.append(poll.getValue());
            if (heap.size() > 0) {
                sb.append(", ");
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        final List<Availability> availabilities = List.of(
            new Availability(Interval.of(10, 100), "Abby"),
            new Availability(Interval.of(20, 40), "Ben"),
            new Availability(Interval.of(60, 70), "Clare"),
            new Availability(Interval.of(120, 180), "Don"),
            new Availability(Interval.of(30, 150), "Eddie"),
            new Availability(Interval.of(30, 100), "Ford"),
            new Availability(Interval.of(30, 100), "Grace"),
            new Availability(Interval.of(210, 250), "Hugh"),
            new Availability(Interval.of(40, 40), "Irene")
        );

        final Map<Interval, Set<String>> result = function(availabilities);

        final Map<Interval, Set<String>> result2 = functionNLogN(
            availabilities
        );

        final Map<Interval, Set<String>> result3 = functionNLogNCleaner(
            availabilities
        );

        final Map<Interval, Set<String>> result4 = nLogNShort(availabilities);

        print(result);
        print(result2);
        print(result3);
        print(result4);
    }
}
