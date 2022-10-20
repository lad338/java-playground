package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class MaxLenOfSubarrayWithPositiveProduct1567 {

    class Solution {

        public int getMaxLen(int[] nums) {
            List<List<Integer>> arrays = new ArrayList<>();
            List<Integer> countingList = new ArrayList<>();
            int max = 0;

            for (int i = 0; i < nums.length; i++) {
                int current = nums[i];
                if (current != 0) {
                    countingList.add(current);
                } else {
                    if (countingList.size() > 0) {
                        arrays.add(countingList);
                    }
                    countingList = new ArrayList<>();
                }
            }
            if (countingList.size() > 0) {
                arrays.add(countingList);
            }

            if (arrays.size() == 0) {
                return 0;
            }

            for (List<Integer> currentArray : arrays) {
                int firstNegative = -1;
                int negativeCount = 0;
                int finalNegative = -1;
                for (int j = 0; j < currentArray.size(); j++) {
                    if (currentArray.get(j) < 0) {
                        if (firstNegative < 0) {
                            firstNegative = j;
                        }
                        finalNegative = j;
                        negativeCount++;
                    }
                }
                if (negativeCount % 2 == 0) {
                    max = Math.max(max, currentArray.size());
                } else {
                    max =
                        Math.max(
                            max,
                            Math.max(
                                Math.max(
                                    finalNegative,
                                    currentArray.size() - 1 - finalNegative
                                ),
                                Math.max(
                                    firstNegative,
                                    currentArray.size() - firstNegative - 1
                                )
                            )
                        );
                }
            }
            return max;
        }
    }

    class ONSolution {

        public int getMaxLen(int[] nums) {
            int positive = 0;
            int negative = 0;
            int max = 0;

            for (int current : nums) {
                if (current == 0) {
                    max = Math.max(max, positive);
                    positive = 0;
                    negative = 0;
                }

                if (current > 0) {
                    positive++;
                    if (negative > 0) {
                        negative++;
                    }
                }

                if (current < 0) {
                    max = Math.max(max, positive);
                    int temp = positive;
                    positive = negative > 0 ? negative + 1 : 0;
                    negative = temp + 1;
                }
            }

            return Math.max(max, positive);
        }
    }
}
