package leetcode.dp;

import java.util.*;

public class NumberMusicPlaylists920 {

    class Solution {

        final int MOD = (int) 1e9 + 7;

        Map<Integer, Map<Integer, Long>> cache = new HashMap<>();

        public int numMusicPlaylists(int n, int goal, int k) {
            return (int) helper(n, goal, k, 0);
        }

        private long helper(int n, int goal, int k, int setCount) {
            if (goal == 0 && setCount == n) {
                return 1;
            }

            if (goal == 0 || setCount > n) {
                return 0;
            }

            cache.putIfAbsent(goal, new HashMap<>());
            if (cache.get(goal).containsKey(setCount)) {
                return cache.get(goal).get(setCount);
            }

            long result =
                (helper(n, goal - 1, k, setCount + 1)) * (n - setCount);
            result = result % MOD;

            if (setCount > k) {
                long product =
                    (helper(n, goal - 1, k, setCount) * (setCount - k)) % MOD;
                result += product;
                result = result % MOD;
            }

            cache.get(goal).put(setCount, result);
            return (int) result;
        }
    }
}
