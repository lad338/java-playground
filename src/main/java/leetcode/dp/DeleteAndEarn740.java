package leetcode.dp;

import java.util.*;

public class DeleteAndEarn740 {

    class Solution {

        public int deleteAndEarn(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<>();
            List<Integer> uniqueNums = new ArrayList<>();

            for (int num : nums) {
                Integer current = countMap.get(num);
                if (current == null) {
                    uniqueNums.add(num);
                    countMap.put(num, 1);
                } else {
                    countMap.put(num, current + 1);
                }
            }

            uniqueNums.sort(Comparator.naturalOrder());
            System.out.println(uniqueNums);

            int[] results = new int[uniqueNums.size()];
            int previousNumber = uniqueNums.get(0);
            results[0] = countMap.get(uniqueNums.get(0)) * uniqueNums.get(0);
            if (uniqueNums.size() == 1) {
                return results[0];
            }

            for (int i = 1; i < uniqueNums.size(); i++) {
                int current = uniqueNums.get(i);
                int currentSum = countMap.get(current) * current;
                if (previousNumber + 1 == current) {
                    results[i] =
                        Math.max(
                            results[i - 1],
                            (i == 1 ? 0 : results[i - 2]) + currentSum
                        );
                } else {
                    results[i] = currentSum + results[i - 1];
                }
                previousNumber = current;
            }

            return Math.max(
                results[uniqueNums.size() - 1],
                results[uniqueNums.size() - 2]
            );
        }
    }

    class DpSolution {

        public int deleteAndEarn(int[] nums) {
            int n = 10001;
            int[] values = new int[n];
            for (int num : nums) values[num] += num;

            int take = values[1], skip = 0;
            for (int i = 1; i < n; i++) {
                int takei = skip + values[i];
                int skipi = Math.max(skip, take);
                take = takei;
                skip = skipi;
            }
            return Math.max(take, skip);
        }
    }
}
