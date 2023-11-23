package leetcode.slidingWindow;

public class MinimumRecolorsGetKConsecutiveBlackBlocks2379 {

    class Solution {

        public int minimumRecolors(String blocks, int k) {
            int result = k;
            int wCount = 0;
            char[] blocksArray = blocks.toCharArray();
            for (int i = 0; i < k - 1; i++) {
                if (blocksArray[i] == 'W') {
                    wCount++;
                }
            }

            for (int i = k - 1; i < blocks.length(); i++) {
                if (blocksArray[i] == 'W') {
                    wCount++;
                }

                result = Math.min(result, wCount);

                if (blocksArray[i - k + 1] == 'W') {
                    wCount--;
                }
            }

            return result;
        }
    }
}
