package leetcode.greedy;

import java.util.Arrays;

public class BagOfTokens948 {

    class Solution {

        public int bagOfTokensScore(int[] tokens, int power) {
            Arrays.sort(tokens);

            int L = 0;
            int R = tokens.length - 1;
            int result = 0;
            int score = 0;
            while (L <= R) {
                if (power >= tokens[L]) {
                    score++;
                    result = Math.max(result, score);
                    power -= tokens[L];
                    L++;
                } else if (score >= 1) {
                    score--;
                    power += tokens[R];
                    R--;
                } else {
                    break;
                }
            }
            return result;
        }
    }
}
