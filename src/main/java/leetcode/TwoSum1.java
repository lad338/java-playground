package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            final Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                final int sub = target - nums[i];
                if (i > 0) {
                    if (map.containsKey(sub)) {
                        return new int[] {map.get(sub), i};
                    }
                }
                map.put(nums[i], i);
            }
            return new int[] { 0, 1 };
        }
    }
}
