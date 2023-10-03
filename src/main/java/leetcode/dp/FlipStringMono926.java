package leetcode.dp;

public class FlipStringMono926 {

    class Solution {

        public int minFlipsMonoIncr(String s) {
            int result = 0;
            int count1 = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    count1++;
                } else {
                    result = Math.min(count1, 1 + result);
                }
            }

            return result;
        }
    }
}
