package leetcode;

public class LongestPalindromicSubstring5 {

    class FirstSolution {

        public String longestPalindrome(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];
            int startingPointsCount = s.length() * 2 - 1;

            String result = "";

            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
            }

            for (int i = 0; i < startingPointsCount; i++) {
                String current;
                boolean isContinue = true;
                if (i % 2 == 0) {
                    current = "" + s.charAt(i / 2);
                    for (
                        int j = 1;
                        isContinue && i / 2 - j >= 0 && i / 2 + j < s.length();
                        j++
                    ) {
                        if (dp[i / 2 - j][i / 2 + j]) {
                            current =
                                s.charAt(i / 2 - j) +
                                current +
                                s.charAt(i / 2 + j);
                        } else {
                            isContinue = false;
                        }
                    }
                } else {
                    current = "";
                    for (
                        int j = 1;
                        isContinue &&
                        (i - j) / 2 >= 0 &&
                        (i + j) / 2 < s.length();
                        j = j + 2
                    ) {
                        if (dp[(i - j) / 2][(i + j) / 2]) {
                            current =
                                s.charAt((i - j) / 2) +
                                current +
                                s.charAt((i + j) / 2);
                        } else {
                            isContinue = false;
                        }
                    }
                }
                if (current.length() > result.length()) {
                    result = current;
                }
            }
            return result;
        }
    }

    class ExpandAroundCenterSolution {

        public class Result {

            public int len;
            public int l;
            public int r;

            public Result(int len, int l, int r) {
                this.len = len;
                this.l = l;
                this.r = r;
            }
        }

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";
            int newLength = s.length() * 2 - 1;
            int center = newLength / 2;
            int l = center, r = center;
            Result cResult = expandAroundCenter(s, center);
            int maxLen = cResult.len;
            int start = cResult.l;
            int end = cResult.r;
            while (
                l > 0 && maxLen < l * 2 + 1 && maxLen < (newLength - r) * 2 + 1
            ) {
                l--;
                Result lResult = expandAroundCenter(s, l);
                r++;
                Result rResult = expandAroundCenter(s, r);
                if (lResult.len > maxLen) {
                    maxLen = lResult.len;
                    start = lResult.l;
                    end = lResult.r;
                }
                if (rResult.len > maxLen) {
                    maxLen = rResult.len;
                    start = rResult.l;
                    end = rResult.r;
                }
            }
            return s.substring(start, end + 1);
        }

        private Result expandAroundCenter(String s, int centerInNewString) {
            int L = centerInNewString / 2;
            int R = centerInNewString % 2 == 0 ? L : L + 1;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return new Result(R - L - 1, L + 1, R - 1);
        }
    }

    class ProvidedSolutionWithPartitions {

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";
            int newLength = s.length() * 2 - 1;
            int start = 0, end = 0;
            for (int i = 0; i < newLength; i++) {
                int len = expandAroundCenter(s, i);
                if (len > end - start) {
                    start = i / 2 - (len - 1) / 2;
                    end = i / 2 + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int centerInNewString) {
            int L = centerInNewString / 2;
            int R = centerInNewString % 2 == 0 ? L : L + 1;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }
    // a b c d
    // 0123456 7
    // 0 1 2 3 4
    // a b c d e
    // 012345678 9
    // 0 1 2 3 4 5

    // a a b b b b
}
