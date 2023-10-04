package leetcode;

import java.util.*;

public class Permutates46 {

    class Solution {

        public List<List<Integer>> permute(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int i : nums) {
                set.add(i);
            }

            return p(Collections.singletonList(new ArrayList<>()), set);
        }

        private List<List<Integer>> p(
            List<List<Integer>> prefix,
            Set<Integer> pool
        ) {
            System.out.println(prefix);
            if (pool.size() == 0) {
                return prefix;
            } else {
                List<List<Integer>> results = new ArrayList<>();

                for (int current : pool) {
                    List<List<Integer>> updated = new ArrayList<>();
                    Set<Integer> copyPool = new HashSet<>(pool);
                    copyPool.remove(current);
                    for (List<Integer> currentPrefix : prefix) {
                        List<Integer> newPrefix = new ArrayList<>(
                            currentPrefix
                        );
                        newPrefix.add(current);
                        updated.add(newPrefix);
                    }
                    List<List<Integer>> result = p(updated, copyPool);
                    results.addAll(result);
                }
                return results;
            }
        }
    }

    class Solution2 {

        public List<List<Integer>> permute(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) {
                list.add(n);
            }
            return permute(list);
        }

        public List<List<Integer>> permute(List<Integer> nums) {
            List<List<Integer>> result = new ArrayList<>();

            if (nums.size() == 1) {
                return Collections.singletonList(nums);
            }

            for (int i = 0; i < nums.size(); i++) {
                List<Integer> copy = new ArrayList<>(nums);
                copy.remove(i);

                List<List<Integer>> perms = permute(copy);
                for (List<Integer> p : perms) {
                    p.add(nums.get(i));
                    result.add(p);
                }
            }
            return result;
        }
    }
}
