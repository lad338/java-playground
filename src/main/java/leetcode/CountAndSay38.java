package leetcode;

public class CountAndSay38 {

    class Solution {

        public String countAndSay(int n) {
            if (n == 1) {
                return "1";
            }
            return count("1", n - 1);
        }

        public String count(String s, int nLeft) {
            StringBuilder sb = new StringBuilder();
            char holding = ' ';
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if (holding == ' ' || holding == current) {
                    holding = current;
                    count++;
                } else {
                    sb.append(count);
                    sb.append(holding);
                    holding = current;
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(holding);

            if (nLeft == 1) {
                return sb.toString();
            } else {
                return count(sb.toString(), nLeft - 1);
            }
        }
    }
}
