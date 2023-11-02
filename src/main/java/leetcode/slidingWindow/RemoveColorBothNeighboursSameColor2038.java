package leetcode.slidingWindow;

public class RemoveColorBothNeighboursSameColor2038 {

    class Solution {

        public boolean winnerOfGame(String colors) {
            int alice = 0;
            int bob = 0;

            char current = colors.charAt(0);
            int count = 1;
            for (int i = 1; i < colors.length(); i++) {
                if (current == colors.charAt(i)) {
                    count++;
                } else {
                    if (current == 'A') {
                        alice += Math.max(0, count - 2);
                    } else {
                        bob += Math.max(0, count - 2);
                    }
                    count = 1;
                    current = colors.charAt(i);
                }
            }

            if (current == 'A') {
                alice += Math.max(0, count - 2);
            } else {
                bob += Math.max(0, count - 2);
            }

            return alice > bob;
        }
    }
}
