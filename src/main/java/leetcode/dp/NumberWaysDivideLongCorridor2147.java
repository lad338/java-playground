package leetcode.dp;

import java.util.*;

public class NumberWaysDivideLongCorridor2147 {

    class DPSolution {

        int MOD = (int) 1e9 + 7;

        String corridor;

        int[][] cache;

        public int numberOfWays(String corridor) {
            this.corridor = corridor;
            this.cache = new int[corridor.length()][3];
            for (int i = 0; i < corridor.length(); i++) {
                for (int j = 0; j < 3; j++) {
                    cache[i][j] = -1;
                }
            }

            return helper(0, 0);
        }

        private int helper(int index, int seats) {
            if (index == corridor.length()) {
                if (seats == 2) {
                    return 1;
                }
                return 0;
            }

            if (cache[index][seats] != -1) {
                return cache[index][seats];
            }

            long ways = 0;

            if (seats == 2) {
                if (corridor.charAt(index) == 'P') {
                    ways += helper(index + 1, 0) + helper(index + 1, 2);
                } else {
                    ways += helper(index + 1, 1);
                }
            } else {
                if (corridor.charAt(index) == 'P') {
                    ways += helper(index + 1, seats);
                } else {
                    ways += helper(index + 1, seats + 1);
                }
            }

            int result = (int) (ways % MOD);
            cache[index][seats] = result;
            return result;
        }
    }

    class MathSolution {

        int MOD = (int) 1e9 + 7;

        public int numberOfWays(String corridor) {
            List<Integer> charPositions = new ArrayList<>();
            for (int i = 0; i < corridor.length(); i++) {
                if (corridor.charAt(i) == 'S') {
                    charPositions.add(i);
                }
            }

            if (charPositions.size() == 0 || charPositions.size() % 2 == 1) {
                return 0;
            }

            long ways = 1;
            for (int i = 1; i < charPositions.size() - 1; i += 2) {
                ways *= (charPositions.get(i + 1) - charPositions.get(i));
                ways %= MOD;
            }

            return (int) ways;
        }
    }
}
