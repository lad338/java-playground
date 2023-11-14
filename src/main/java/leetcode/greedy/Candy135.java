package leetcode.greedy;

import java.util.Arrays;

public class Candy135 {

    class N2Solution {

        public int candy(int[] ratings) {
            int[] children = new int[ratings.length];

            int result = ratings.length;
            Arrays.fill(children, 1);

            boolean isDone = false;
            while (!isDone) {
                boolean iterationDone = true;
                for (int i = 0; i < children.length; i++) {
                    if (i - 1 >= 0) {
                        if (
                            ratings[i] > ratings[i - 1] &&
                            children[i] <= children[i - 1]
                        ) {
                            iterationDone = false;
                            children[i] += 1;
                            result += 1;
                            continue;
                        }
                    }

                    if (i + 1 < ratings.length) {
                        if (
                            ratings[i] > ratings[i + 1] &&
                            children[i] <= children[i + 1]
                        ) {
                            iterationDone = false;
                            children[i] += 1;
                            result += 1;
                        }
                    }
                }
                isDone = iterationDone;
            }

            return result;
        }
    }

    class TwoPassSolution {

        public int candy(int[] ratings) {
            int[] children = new int[ratings.length];

            Arrays.fill(children, 1);

            int localMaximum = 1;
            for (int i = 1; i < children.length; i++) {
                if (ratings[i - 1] < ratings[i]) {
                    localMaximum += 1;
                    children[i] = localMaximum;
                } else {
                    localMaximum = 1;
                }
            }

            localMaximum = children[ratings.length - 1];

            for (int i = children.length - 2; i >= 0; i--) {
                if (ratings[i + 1] < ratings[i]) {
                    localMaximum = Math.max(localMaximum + 1, children[i]);
                    children[i] = localMaximum;
                } else {
                    localMaximum = children[i];
                }
            }

            int result = 0;

            for (int child : children) {
                result += child;
            }

            // System.out.println(Arrays.stream(children).boxed().toList());

            return result;
        }
    }

    class NeetCodeSolution {

        class Solution {

            public int candy(int[] ratings) {
                int[] children = new int[ratings.length];

                Arrays.fill(children, 1);

                for (int i = 1; i < children.length; i++) {
                    if (ratings[i - 1] < ratings[i]) {
                        children[i] = children[i - 1] + 1;
                    }
                }

                for (int i = children.length - 2; i >= 0; i--) {
                    if (ratings[i + 1] < ratings[i]) {
                        children[i] =
                            Math.max(children[i], children[i + 1] + 1);
                    }
                }
                return Arrays.stream(children).sum();
            }
        }
    }
}
