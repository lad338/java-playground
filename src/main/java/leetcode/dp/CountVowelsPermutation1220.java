package leetcode.dp;

public class CountVowelsPermutation1220 {

    class Solution {

        int[][] cache;
        int MOD = (int) 1e9 + 7;

        public int countVowelPermutation(int n) {
            cache = new int[5][n + 1];
            for (int i = 0; i < 5; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j == 1) {
                        cache[i][j] = 1;
                    } else {
                        cache[i][j] = -1;
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < 5; i++) {
                result += helper(i, n);
                result %= MOD;
            }
            return result;
        }

        // find number possiblity that a string ends with such vowel with such length
        private int helper(int vowel, int length) {
            if (cache[vowel][length] != -1) {
                return cache[vowel][length];
            }

            int count = 0;

            if (vowel == 0) { // a: preceeding = e, i, u (1, 2, 4)
                count += helper(1, length - 1);
                count %= MOD;
                count += helper(2, length - 1);
                count %= MOD;
                count += helper(4, length - 1);
                count %= MOD;
            } else if (vowel == 1) { // e, preceeding = a, i (0, 2)
                count += helper(0, length - 1);
                count %= MOD;
                count += helper(2, length - 1);
                count %= MOD;
            } else if (vowel == 2) { //i; preceeding = e, o (1, 3)
                count += helper(1, length - 1);
                count %= MOD;
                count += helper(3, length - 1);
                count %= MOD;
            } else if (vowel == 3) { // o; preceeding = i (2)
                count += helper(2, length - 1);
                count %= MOD;
            } else { //u; preceeding = i, o (2, 3)
                count += helper(2, length - 1);
                count %= MOD;
                count += helper(3, length - 1);
                count %= MOD;
            }
            cache[vowel][length] = count;
            return cache[vowel][length];
        }
    }
}
