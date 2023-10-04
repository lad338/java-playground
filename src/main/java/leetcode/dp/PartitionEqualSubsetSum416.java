package leetcode.dp;

import java.util.*;

public class PartitionEqualSubsetSum416 {

    class Solution {

        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            if (sum % 2 == 1) {
                return false;
            }
            int half = sum / 2;

            Set<Integer> dp = new HashSet<>();
            dp.add(0);

            for (int n : nums) {
                Set<Integer> copy = new HashSet<>(dp);

                for (Integer i : dp) {
                    if (i + n == half) {
                        return true;
                    }
                    copy.add(i + n);
                }
                dp = copy;
            }

            return dp.contains(half);
        }
    }
}
