package leetcode.dp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RegularExpressionMatching10 {

    class InitialSolution {

        public boolean isMatch(String s, String p) {
            int sIndex = 0;
            int pIndex = 0;

            return isMatchFromIndex(s, optimize(p), sIndex, pIndex);
        }

        private String optimize(String p) {
            List<String> tokens = new ArrayList<>();
            for (int i = 0; i < p.length(); i++) {
                String token = "";
                char pChar = p.charAt(i);
                if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                    token += pChar + "*";
                    i++;
                } else {
                    token += pChar;
                }
                tokens.add(token);
            }

            for (int i = 0; i < tokens.size(); i++) {
                if (i + 1 < tokens.size()) {
                    if (
                        tokens.get(i).length() == 2 &&
                        tokens.get(i).equals(tokens.get(i + 1))
                    ) {
                        tokens.remove(i + 1);
                        i--;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (String token : tokens) {
                sb.append(token);
            }

            return sb.toString();
        }

        private boolean isMatchFromIndex(
            String s,
            String p,
            int sIndex,
            int pIndex
        ) {
            for (int i = pIndex; i < p.length(); i++) {
                char pChar = p.charAt(i);

                if (i + 1 < p.length()) {
                    char pNextChar = p.charAt(i + 1);
                    if (pNextChar == '*') {
                        i++;
                        Deque<Integer> stack = new ArrayDeque<>();
                        stack.push(sIndex - 1);
                        for (int j = sIndex; j < s.length(); j++) {
                            if (pChar == '.') {
                                //TODO optimize
                                stack.push(j);
                            } else if (pChar == s.charAt(j)) {
                                stack.push(j);
                            } else {
                                j = s.length();
                            }
                        }

                        Deque<Integer> foundS = new ArrayDeque<>();

                        while (stack.size() != 0) {
                            //recursively call this
                            int sIndex2 = stack.pop();
                            boolean found = isMatchFromIndex(
                                s,
                                p,
                                sIndex2 + 1,
                                i + 1
                            );

                            if (found) {
                                foundS.push(sIndex2 + 1);
                                return true;
                            } else if (stack.size() == 0) {
                                return false;
                            }
                        }

                        continue;
                    }
                }

                if (sIndex >= s.length()) {
                    return false;
                }

                if (!(pChar == '.' || (pChar == s.charAt(sIndex)))) {
                    return false;
                } else {
                    sIndex++;
                }
            }
            return sIndex == s.length();
        }
    }

    class DpSolution {

        public boolean isMatch(String s, String p) {
            if (p.length() == 0) return s.length() == 0;

            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            dp[0][0] = true;

            for (int j = 2; j < dp[0].length; j++) {
                dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
            }

            for (int j = 1; j < dp[0].length; j++) {
                for (int i = 1; i < dp.length; i++) {
                    if (
                        p.charAt(j - 1) == '.' ||
                        p.charAt(j - 1) == s.charAt(i - 1)
                    ) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] =
                            dp[i][j - 2] ||
                            (
                                (
                                    p.charAt(j - 2) == '.' ||
                                    s.charAt(i - 1) == p.charAt(j - 2)
                                ) &&
                                dp[i - 1][j]
                            );
                    }
                }
            }

            return dp[s.length()][p.length()];
        }
    }
}
