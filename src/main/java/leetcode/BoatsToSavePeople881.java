package leetcode;

import java.util.*;

public class BoatsToSavePeople881 {

    class InitialSolution {

        public int numRescueBoats(int[] people, int limit) {
            List<Integer> peopleList = new ArrayList<>();

            for (int person : people) {
                peopleList.add(person);
            }

            Collections.sort(peopleList);

            int smallPointer = -1;
            int largePointer = peopleList.size() - 1;
            int result = 0;

            while (largePointer > smallPointer) {
                int largePerson = peopleList.get(largePointer);
                largePointer--;
                if (limit - largePerson > 0) {
                    int smallPerson = peopleList.get(smallPointer + 1);
                    if (smallPerson + largePerson <= limit) {
                        smallPointer++;
                    }
                }
                result++;
            }

            return result;
        }
    }

    class UpdatedSolution {

        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);

            int smallPointer = 0;
            int largePointer = people.length - 1;
            int result = 0;

            while (largePointer > smallPointer) {
                int largePerson = people[largePointer];
                largePointer--;
                if (limit - largePerson > 0) {
                    int smallPerson = people[smallPointer + 1];
                    if (smallPerson + largePerson <= limit) {
                        smallPointer++;
                    }
                }
                result++;
            }

            return result;
        }
    }

    class OptimizedSolution {

        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);

            int smallPointer = 0;
            int largePointer = people.length - 1;
            int result = 0;

            while (largePointer >= smallPointer) {
                if (people[smallPointer] + people[largePointer] <= limit) {
                    largePointer--;
                    smallPointer++;
                } else {
                    largePointer--;
                }
                result++;
            }

            return result;
        }
    }
}
