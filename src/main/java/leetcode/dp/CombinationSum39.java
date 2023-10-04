package leetcode.dp;

import java.util.*;

public class CombinationSum39 {

    class Solution {

        private Map<Integer, Set<List<Integer>>> dp = new HashMap<>();

        public List<List<Integer>> combinationSum(
            int[] candidates,
            int target
        ) {
            recursive(candidates, target);
            return new ArrayList<>(
                dp.getOrDefault(target, Collections.emptySet())
            );
        }

        private void recursive(int[] candidates, int target) {
            if (dp.containsKey(target)) {
                return;
            }

            for (int num : candidates) {
                if (num == target) {
                    final Set<List<Integer>> existing = dp.getOrDefault(
                        target,
                        new HashSet<>()
                    );
                    List<Integer> result = new ArrayList<>();
                    result.add(target);
                    existing.add(result);
                    dp.put(target, existing);
                }
                if (num < target) {
                    int diff = target - num;
                    recursive(candidates, diff);
                    Set<List<Integer>> diffSet = dp.getOrDefault(
                        diff,
                        new HashSet<>()
                    );

                    Set<List<Integer>> result = dp.getOrDefault(
                        target,
                        new HashSet<>()
                    );

                    for (List<Integer> list : diffSet) {
                        final List<Integer> copy = new ArrayList<>(list);
                        copy.add(num);
                        copy.sort(Integer::compareTo);
                        result.add(copy);
                    }
                    dp.put(target, result);
                }
            }
        }
    }

    class BacktrackingSolution {

        private List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum(
            int[] candidates,
            int target
        ) {
            dfs(0, new ArrayList<>(), 0, target, candidates);
            return result;
        }

        private void dfs(
            int i,
            List<Integer> cur,
            int total,
            int target,
            int[] candidates
        ) {
            if (total == target) {
                result.add(new ArrayList<>(cur));
                return;
            }

            if (i >= candidates.length || total > target) {
                return;
            }

            cur.add(candidates[i]);
            dfs(i, cur, total + candidates[i], target, candidates);
            cur.remove(cur.size() - 1);
            dfs(i + 1, cur, total, target, candidates);
        }
    }
}
