package leetcode;

public class KokoEatingBananas875 {

    class Solution {

        public int minEatingSpeed(int[] piles, int h) {
            int max = 0;
            for (int pile : piles) {
                max = Math.max(max, pile);
            }

            int L = 1;
            int R = max;
            int result = Integer.MAX_VALUE;

            while (L <= R) {
                int M = L + (R - L) / 2;

                int index = 0;
                int hoursLeft = h;
                while (
                    index < piles.length &&
                    hoursLeft - (int) Math.ceil(piles[index] / (double) M) >= 0
                ) {
                    hoursLeft -= (int) Math.ceil(piles[index] / (double) M);
                    index++;
                }

                if (index >= piles.length) {
                    result = M;
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }

            return result;
        }
    }
}
