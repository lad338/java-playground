package leetcode;

import java.util.*;

public class ThreeSum15 {

    class Solution {

        //This problem is rigid
        // public List<List<Integer>> threeSum(int[] nums) {
        //     List<List<Integer>> results = new ArrayList<>();
        //     for (int i = 0; i < nums.length; i++) {
        //         int target = -nums[i];
        //         Map<Integer, List<Integer>> map = new HashMap<>();
        //         for (int j = i + 1; j < nums.length; j ++) {

        //             List<Integer> l = map.getOrDefault(
        //                 nums[j],
        //                 new ArrayList<>()
        //             );

        //             if (l.size() >= 1) {
        //                 for (int k = 0; k < l.size(); k++) {
        //                     List<Integer> result = new ArrayList<>();
        //                     result.add(nums[i]);
        //                     result.add(nums[j]);
        //                     result.add(nums[l.get(k)]);
        //                     results.add(result);
        //                 }
        //             }

        //             l.add(j);

        //             map.put (
        //                 target - nums[j],
        //                 l
        //             );
        //         }
        //     }
        //     return results;
        // }

        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);

            Set<List<Integer>> results = new HashSet<>();

            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        results.add(List.of(nums[i], nums[j], nums[k]));
                        k--;
                        j++;
                    } else if (sum > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }

            return new ArrayList<>(results);
        }
    }
}
