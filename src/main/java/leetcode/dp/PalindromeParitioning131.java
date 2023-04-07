package leetcode.dp;

import java.util.*;

public class PalindromeParitioning131 {

    class NotDpSolution {

        public List<List<String>> partition(String s) {
            if (s.length() == 1) {
                return List.of(List.of(s));
            }
            Set<List<String>> results = new HashSet<>();

            List<String> tokens = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                tokens.add("" + s.charAt(i));
            }
            results.add(tokens);

            recursive(results, tokens);
            return new ArrayList<>(results);
        }

        private void recursive(Set<List<String>> results, List<String> tokens) {
            List<List<String>> newResults = new ArrayList<>();

            for (int i = 0; i < tokens.size() - 1; i++) {
                if (isReverseOf(tokens.get(i), tokens.get(i + 1))) {
                    List<String> cal = new ArrayList<>();
                    for (int j = 0; j < i; j++) {
                        cal.add(tokens.get(j));
                    }
                    cal.add(tokens.get(i) + tokens.get(i + 1));
                    for (int j = i + 2; j < tokens.size(); j++) {
                        cal.add(tokens.get(j));
                    }
                    newResults.add(cal);
                }

                if (i >= 1) {
                    List<String> cal2 = new ArrayList<>();
                    if (isReverseOf(tokens.get(i - 1), tokens.get(i + 1))) {
                        for (int j = 0; j < i - 1; j++) {
                            cal2.add(tokens.get(j));
                        }
                        cal2.add(
                            tokens.get(i - 1) +
                            tokens.get(i) +
                            tokens.get(i + 1)
                        );
                        for (int j = i + 2; j < tokens.size(); j++) {
                            cal2.add(tokens.get(j));
                        }
                        newResults.add(cal2);
                    }
                }
            }

            if (newResults.size() == 0) {
                return;
            } else {
                for (List<String> t : newResults) {
                    if (!results.contains(t)) {
                        results.add(t);
                        recursive(results, t);
                    }
                }
            }
        }

        private boolean isReverseOf(String a, String b) {
            if (a.length() != b.length()) {
                return false;
            }
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
