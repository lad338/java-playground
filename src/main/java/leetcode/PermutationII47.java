package leetcode;

import java.util.*;

public class PermutationII47 {

    class Solution {

        public List<List<Integer>> permuteUnique(int[] nums) {
            Set<List<Integer>> initialSet = new HashSet<>();
            initialSet.add(new ArrayList<>());
            return new ArrayList<>(
                helper(initialSet, Arrays.stream(nums).boxed().toList())
            );
        }

        private Set<List<Integer>> helper(
            Set<List<Integer>> prefixes,
            List<Integer> nums
        ) {
            if (nums.isEmpty()) {
                return prefixes;
            }

            Set<List<Integer>> result = new HashSet<>();

            for (int i = 0; i < nums.size(); i++) {
                List<Integer> copyNums = new ArrayList<>(nums);
                copyNums.remove(i);

                Set<List<Integer>> newPrefixes = new HashSet<>();
                for (List<Integer> prefix : prefixes) {
                    List<Integer> copyPrefix = new ArrayList<>(prefix);
                    copyPrefix.add(nums.get(i));
                    newPrefixes.add(copyPrefix);
                }
                result.addAll(helper(newPrefixes, copyNums));
            }

            return result;
        }
    }
}
