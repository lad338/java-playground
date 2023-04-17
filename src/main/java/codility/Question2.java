package codility;

public class Question2 {

    class Solution {

        public int solution(int N) {
            // Implement your solution here
            if (N == 5) {
                return 0;
            }

            String nString = String.valueOf(N);

            int result = Integer.MIN_VALUE;

            for (int i = 0; i < nString.length(); i++) {
                if (nString.charAt(i) == '5') {
                    String newString =
                        nString.substring(0, i) + nString.substring(i + 1);

                    result = Math.max(result, Integer.parseInt(newString));
                }
            }

            return result;
        }
    }
}
