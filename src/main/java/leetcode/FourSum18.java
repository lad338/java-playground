package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class FourSum18 {

    class Solution {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            return xSum(
                new ArrayList<>(),
                Arrays
                    .stream(nums)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList()),
                0,
                target,
                4
            );
        }

        private List<List<Integer>> xSum(
            List<Integer> prefix,
            List<Integer> sortedNums,
            int l,
            long target,
            int x
        ) {
            if (x == 2) {
                return twoSum(prefix, sortedNums, l, target);
            }

            List<List<Integer>> result = new ArrayList<>();

            int left = l;

            while (left < sortedNums.size() - 2) {
                int val = sortedNums.get(left);
                List<Integer> nextPrefix = new ArrayList<>(prefix);
                nextPrefix.add(val);

                left++;

                List<List<Integer>> xSumResults = xSum(
                    nextPrefix,
                    sortedNums,
                    left,
                    target - val,
                    x - 1
                );
                while (
                    left < sortedNums.size() - 1 &&
                    sortedNums.get(left).equals(val)
                ) {
                    left++;
                }
                result.addAll(xSumResults);
            }

            return result;
        }

        private List<List<Integer>> twoSum(
            List<Integer> prefix,
            List<Integer> sortedNums,
            int left,
            long target
        ) {
            List<List<Integer>> result = new ArrayList<>();
            int l = left;
            int r = sortedNums.size() - 1;

            while (l < r) {
                int sum = sortedNums.get(l) + sortedNums.get(r);
                if (sum == target) {
                    List<Integer> combination = new ArrayList<>(prefix);
                    combination.add(sortedNums.get(l));
                    combination.add(sortedNums.get(r));
                    result.add(combination);
                    l++;
                    r--;
                    while (
                        l < r && sortedNums.get(l - 1).equals(sortedNums.get(l))
                    ) {
                        l++;
                    }
                    if (l >= r) {
                        break;
                    }
                    while (
                        r > l && sortedNums.get(r + 1).equals(sortedNums.get(r))
                    ) {
                        r--;
                    }
                    if (r <= l) {
                        break;
                    }
                } else if (sum > target) {
                    r--;
                    while (
                        r > l && sortedNums.get(r).equals(sortedNums.get(r + 1))
                    ) {
                        r--;
                    }
                    if (r <= l) {
                        break;
                    }
                } else {
                    l++;
                    while (
                        l < r && sortedNums.get(l).equals(sortedNums.get(l - 1))
                    ) {
                        l++;
                    }
                    if (r <= l) {
                        break;
                    }
                }
            }
            return result;
        }
    }

    class NeetCodeSolution {

        private List<Integer> sortedNums = new ArrayList<>();
        private List<Integer> prefix = new ArrayList<>();
        private List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> fourSum(int[] nums, int target) {
            sortedNums =
                Arrays
                    .stream(nums)
                    .boxed()
                    .sorted()
                    .collect(Collectors.toList());
            xSum(4, 0, target);
            return result;
        }

        private void xSum(int x, int start, long target) {
            if (x != 2) {
                for (int i = start; i < sortedNums.size() - x + 1; i++) {
                    if (
                        i > start &&
                        sortedNums.get(i).equals(sortedNums.get(i - 1))
                    ) {
                        continue;
                    }
                    prefix.add(sortedNums.get(i));
                    xSum(x - 1, i + 1, target - sortedNums.get(i));
                    prefix.remove(prefix.size() - 1);
                }
                return;
            }

            int l = start;
            int r = sortedNums.size() - 1;
            while (l < r) {
                int sum = sortedNums.get(l) + sortedNums.get(r);
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    List<Integer> combination = new ArrayList<>(prefix);
                    combination.add(sortedNums.get(l));
                    combination.add(sortedNums.get(r));
                    result.add(combination);
                    l++;
                    while (
                        l < r && sortedNums.get(l).equals(sortedNums.get(l - 1))
                    ) {
                        l++;
                    }
                }
            }
        }
    }
}
