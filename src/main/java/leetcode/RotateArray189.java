package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RotateArray189 {

    class InitialSolution {

        public void rotate(int[] nums, int k) {
            k = k % nums.length;

            List<Integer> list = new ArrayList<>();

            for (int i = nums.length - k; i < nums.length; i++) {
                list.add(nums[i]);
            }

            for (int i = 0; i < nums.length - k; i++) {
                list.add(nums[i]);
            }

            for (int i = 0; i < list.size(); i++) {
                nums[i] = list.get(i);
            }
        }
    }

    class O1SpaceSolution {

        public void rotate(int[] nums, int k) {
            int n = nums.length;
            if (n == 1) {
                return;
            }
            if (k > n) k %= n;

            reverse(nums, 0, n - k - 1);
            reverse(nums, n - k, n - 1);
            reverse(nums, 0, n - 1);
        }

        private void reverse(int[] nums, int start, int end) {
            int temp;
            while (end > start) {
                temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
