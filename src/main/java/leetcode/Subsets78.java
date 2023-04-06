package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Subsets78 {

    class Solution {

        public List<List<Integer>> subsets(int[] nums) {
            if (nums.length == 0) {
                return Collections.emptyList();
            }
            if (nums.length == 1) {
                return List.of(Collections.emptyList(), List.of(nums[0]));
            }

            List<List<Integer>> results = new ArrayList<>();
            results.add(new ArrayList<>());

            for (int n : nums) {
                List<List<Integer>> newList = new ArrayList<>();
                for (List<Integer> result : results) {
                    List<Integer> current = new ArrayList<>(result);
                    current.add(n);
                    newList.add(current);
                }
                results.addAll(newList);
            }

            return results;
        }
    }
}
