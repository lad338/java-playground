package leetcode;

import java.util.*;

public class IsSubsequence392 {

    class Solution {

        public boolean isSubsequence(String s, String t) {
            if (s.length() == 0) {
                return true;
            }

            int indexS = 0;
            int indexT = 0;
            while (indexT < t.length()) {
                if (s.charAt(indexS) == t.charAt(indexT)) {
                    if (indexS == s.length() - 1) {
                        return true;
                    }
                    indexS++;
                }
                indexT++;
            }
            return false;
        }
    }

    class RecursiveSolution {

        public boolean isSubsequence(String s, String t) {
            if (s.length() == 0) {
                return true;
            }

            if (s.length() == 1) {
                return t.contains(s);
            }

            int index = t.indexOf(s.charAt(0));
            if (index < 0) {
                return false;
            }
            return isSubsequence(s.substring(1), t.substring(index + 1));
        }
    }

    class FollowupSolution {

        Map<Character, List<Integer>> map = new HashMap<>();

        public boolean isSubsequence(String s, String t) {
            constructMap(t);
            return binarySearchMap(s);
        }

        private void constructMap(String t) {
            for (int i = 0; i < t.length(); i++) {
                List<Integer> cList = map.getOrDefault(
                    t.charAt(i),
                    new ArrayList<>()
                );
                cList.add(i);
                map.put(t.charAt(i), cList);
            }
        }

        private boolean binarySearchMap(String s) {
            int index = 0;
            for (char c : s.toCharArray()) {
                List<Integer> cList = map.getOrDefault(
                    c,
                    Collections.emptyList()
                );
                if (cList.size() == 0) {
                    return false;
                }
                int binarySearch = Collections.binarySearch(cList, index);

                if (binarySearch < 0) {
                    binarySearch = -binarySearch - 1;
                }

                if (binarySearch == cList.size()) {
                    return false;
                }

                index = cList.get(binarySearch) + 1;
            }

            return true;
        }
    }
}
