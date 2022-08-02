package leetcode;

public class ArrayStrictlyIncreasing1909 {

  static class Solution {

    public boolean canBeIncreasing(int[] nums) {
      boolean hasOne = false;
      int skipIndex = -1;
      for (int i = 0; i < nums.length; i++) {
        int current = nums[i];
        if (i >= 1) {
          int compareNumber = i - 1 == skipIndex ? nums[i - 2] : nums[i - 1];
          if (current <= compareNumber) {
            if (hasOne) {
              return false;
            }
            //set skipIndex
            //either i-1 is skip or i skip
            //if i - 1 is skip, not setting it does not matter

            //consider i is the skip number

            if (i + 1 < nums.length && nums[i + 1] > compareNumber || current == compareNumber) {
              skipIndex = i;
            } else if (i - 2 >= 0 && current <= nums[i - 2] && i != nums.length - 1) {
              return false;
            }

            hasOne = true;
          }
        }
      }
      return true;
    }
  }

  static class Solution2 {

    public boolean canBeIncreasing(int[] nums) {
      if (nums.length <= 2) {
        return true;
      }
      boolean key = false;
      for (int i = 1; i < nums.length; i++) {
        if (nums[i] <= nums[i - 1]) {
          if (key) {
            return false;
          }
          key = true;
          if (i > 1 && nums[i] <= nums[i - 2]) {
            nums[i] = nums[i - 1];
          }
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.canBeIncreasing(new int[] { 391, 993, 703, 903 }));
  }
}
