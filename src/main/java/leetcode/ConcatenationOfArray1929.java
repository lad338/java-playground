package leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ConcatenationOfArray1929 {

    class NaiveSolution {

        public int[] getConcatenation(int[] nums) {
            int[] results = new int[nums.length * 2];
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < nums.length; i++) {
                    results[i + j * nums.length] = nums[i];
                }
            }
            return results;
        }
    }

    class IdeOptimizedNaiveSolition {

        public int[] getConcatenation(int[] nums) {
            int[] results = new int[nums.length * 2];
            for (int j = 0; j < 2; j++) {
                System.arraycopy(
                    nums,
                    0,
                    results,
                    j * nums.length,
                    nums.length
                );
            }
            return results;
        }
    }

    class OneLinerSolution {

        public int[] getConcatenation(int[] nums) {
            return IntStream
                .concat(Arrays.stream(nums), Arrays.stream(nums))
                .toArray();
        }
    }
}
