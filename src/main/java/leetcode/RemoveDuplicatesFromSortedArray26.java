package leetcode;

public class RemoveDuplicatesFromSortedArray26 {

  static class Solution {

    public int removeDuplicates(int[] nums) {
      int previous = nums[0];
      int uniqueIndex = 0;
      for (int i = 1; i < nums.length; i++) {
        int current = nums[i];
        if (previous != current) {
          uniqueIndex++;
          nums[uniqueIndex] = current;
          previous = current;
        }
      }
      return uniqueIndex + 1;
    }
  }
}
