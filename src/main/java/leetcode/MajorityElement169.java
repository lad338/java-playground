package leetcode;

public class MajorityElement169 {

    class Solution {

        public int majorityElement(int[] nums) {
            int count = 1;
            int value = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) {
                    value = nums[i];
                    count = 1;
                } else {
                    if (nums[i] == value) {
                        count++;
                    } else {
                        count--;
                    }
                }
            }
            return value;
        }
    }
}
