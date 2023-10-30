package leetcode.slidingWindow;

public class PermutationInString567 {

    class Solution {

        public boolean checkInclusion(String s1, String s2) {
            int[] required = new int[26];
            int[] map = new int[26];
            int s1Length = s1.length();
            for (int i = 0; i < s1Length; i++) {
                required[s1.charAt(i) - 'a']++;
            }

            int L = 0;
            for (int R = 0; R < s2.length(); R++) {
                map[s2.charAt(R) - 'a']++;
                if (R - L >= s1Length) {
                    map[s2.charAt(L) - 'a']--;
                    L++;
                }
                if (R - L + 1 == s1Length) {
                    if (helper(required, map)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean helper(int[] a, int[] b) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
