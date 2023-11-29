package leetcode.interval;

import java.util.*;

public class NumberFlowersFullBloom2251 {

    class Solution {

        public static class Person {

            public Integer index;
            public Integer time;

            public Person(int index, int time) {
                this.index = index;
                this.time = time;
            }
        }

        public static class Flower {

            public Integer start;
            public Integer end;

            public Flower(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public int[] fullBloomFlowers(int[][] flowers, int[] people) {
            int[] result = new int[people.length];
            List<Person> sorted = new ArrayList<>();
            List<Flower> sortedFlowers = new ArrayList<>();
            PriorityQueue<Integer> queue = new PriorityQueue<>();

            for (int i = 0; i < people.length; i++) {
                sorted.add(new Person(i, people[i]));
            }
            for (int[] flower : flowers) {
                sortedFlowers.add(new Flower(flower[0], flower[1]));
            }

            sorted.sort(Comparator.comparing(a -> a.time));
            sortedFlowers.sort(Comparator.comparing(a -> a.start));

            int index = 0;
            for (Person person : sorted) {
                while (
                    index < sortedFlowers.size() &&
                    sortedFlowers.get(index).start <= person.time
                ) {
                    queue.add(sortedFlowers.get(index).end);
                    index++;
                }
                while (queue.size() > 0 && queue.peek() < person.time) {
                    queue.poll();
                }
                result[person.index] = queue.size();
            }

            return result;
        }
    }

    class NoClassSolution {

        public int[] fullBloomFlowers(int[][] flowers, int[] people) {
            int[] result = new int[people.length];
            int[] sortedPeople = Arrays.copyOf(people, people.length);
            Arrays.sort(sortedPeople);
            Arrays.sort(flowers, Arrays::compare);
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            Map<Integer, Integer> resultMap = new HashMap<>();

            int index = 0;
            for (int person : sortedPeople) {
                while (index < flowers.length && flowers[index][0] <= person) {
                    heap.add(flowers[index][1]);
                    index++;
                }
                while (heap.size() > 0 && heap.peek() < person) {
                    heap.poll();
                }
                resultMap.put(person, heap.size());
            }

            for (int i = 0; i < people.length; i++) {
                result[i] = resultMap.get(people[i]);
            }
            return result;
        }
    }
}
