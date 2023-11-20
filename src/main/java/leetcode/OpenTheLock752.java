package leetcode;

import java.util.*;

public class OpenTheLock752 {

    class Solution {

        public int openLock(String[] deadends, String target) {
            if (target.equals("0000")) {
                return 0;
            }

            Set<String> deadendSet = new HashSet<>();
            for (String deadend : deadends) {
                if (deadend.equals("0000")) {
                    return -1;
                }
                deadendSet.add(deadend);
            }

            Set<String> visited = new HashSet<>();
            Queue<String> queue = new ArrayDeque<>();
            int result = 1;
            queue.offer("0000");
            while (!queue.isEmpty()) {
                List<String> toCheck = new ArrayList<>(queue);
                queue.clear();

                for (String checkString : toCheck) {
                    char[] charArray = checkString.toCharArray();
                    for (int i = 0; i < 4; i++) {
                        int originalNumber = charArray[i] - '0';
                        int negative = (originalNumber + 9) % 10;
                        int positive = (originalNumber + 11) % 10;

                        for (int num : new int[] { negative, positive }) {
                            char[] copy = Arrays.copyOfRange(charArray, 0, 4);
                            copy[i] = Character.forDigit(num, 10);

                            String numString = new String(copy);
                            if (numString.equals(target)) {
                                return result;
                            }
                            if (
                                !deadendSet.contains(numString) &&
                                !visited.contains(numString)
                            ) {
                                visited.add(numString);
                                queue.offer(numString);
                            }
                        }
                    }
                }
                result++;
            }

            return -1;
        }
    }
}
