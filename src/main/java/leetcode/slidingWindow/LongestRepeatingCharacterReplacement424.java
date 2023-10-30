package leetcode.slidingWindow;

public class LongestRepeatingCharacterReplacement424 {

    class Solution {

        public int characterReplacement(String s, int k) {
            int[] cache = new int[26];

            int max = 0;
            int result = 0;

            int left = 0;
            for (int right = 0; right < s.length(); right++) {
                char current = s.charAt(right);
                cache[current - 'A'] = cache[current - 'A'] + 1;
                max = Math.max(cache[current - 'A'], max);
                while (right - left + 1 - max > k) {
                    char remove = s.charAt(left);
                    cache[remove - 'A'] = cache[remove - 'A'] - 1;
                    left++;
                }
                if (right - left + 1 - max <= k) {
                    result = Math.max(result, right - left + 1);
                }
            }
            return result;
        }
    }
}
