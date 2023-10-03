package leetcode.dp;

import java.util.*;

public class LongestIncreasingSubsequence300 {

    class NLogNSolution {

        public int lengthOfLIS(int[] nums) {
            List<Deque<Integer>> piles = new ArrayList<>();

            for (int num : nums) {
                if (
                    piles.size() == 0 ||
                    num > piles.get(piles.size() - 1).peek()
                ) {
                    Deque<Integer> newPile = new ArrayDeque<>();
                    newPile.push(num);
                    piles.add(newPile);
                } else {
                    List<Integer> topCards = piles
                        .stream()
                        .map(Deque::peek)
                        .toList();

                    int index = binarySearchPileToPlaceOn(topCards, num);
                    piles.get(index).push(num);
                }
            }

            return piles.size();
        }

        private int binarySearchPileToPlaceOn(
            List<Integer> topCards,
            int value
        ) {
            int index = Collections.binarySearch(topCards, value);
            if (index < 0) {
                return (-index - 1);
            } else {
                return index;
            }
        }
    }

    class DPSolution {

        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];

            Arrays.fill(dp, 1);

            for (int i = nums.length - 1; i >= 0; i--) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                    }
                }
            }

            return Arrays.stream(dp).max().getAsInt();
        }
    }
}
