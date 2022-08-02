package leetcode;

import java.util.Arrays;

public class SearchInsertPosition35 {

  static class Solution {

    public int searchInsert(int[] nums, int target) {
      int length = nums.length;
      if (length == 0) {
        return 0;
      }
      if (length == 1) {
        if (nums[0] >= target) {
          return 0;
        }
        return 1;
      }

      int middlePoint = length / 2;
      if (nums[middlePoint] == target) {
        return middlePoint;
      }
      if (nums[middlePoint] > target) {
        return searchInsert(Arrays.copyOfRange(nums, 0, middlePoint), target);
      }
      if (nums[middlePoint] < target) {
        if (middlePoint + 1 > length) {
          return middlePoint + length + 1;
        } else {
          return (
            middlePoint +
            searchInsert(Arrays.copyOfRange(nums, middlePoint + 1, length), target) +
            1
          );
        }
      }
      throw null;
    }
  }
}
