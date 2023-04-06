package leetcode;

public class FindFirstAndLastPositionInSortedArray34 {

    class Solution {

        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[] { -1, -1 };
            }
            if (nums.length == 1) {
                if (target == nums[0]) {
                    return new int[] { 0, 0 };
                } else {
                    return new int[] { -1, -1 };
                }
            }

            int a = 0;
            int b = nums.length - 1;
            int lower = a;
            int upper = b;
            int found = -1;
            int resultA = -1;
            int resultB = -1;
            boolean resultAFound = false;
            boolean resultBFound = false;
            while (a < b && found == -1) {
                int mid = (a + b) / 2;
                if (nums[a] == target) {
                    resultA = a;
                    resultAFound = true;
                    found = a;
                }

                if (nums[b] == target) {
                    resultB = b;
                    resultBFound = true;
                    found = b;
                }

                if (nums[mid] == target) {
                    found = mid;
                }
                if (found > 0) {
                    break;
                }

                if (target > nums[mid]) {
                    a = mid + 1;
                    lower = mid;
                } else {
                    b = mid - 1;
                    upper = mid;
                }
            }

            if (found == -1) {
                return new int[] { -1, -1 };
            }

            int lowerA = lower;
            int upperA = found;

            while (lowerA < upperA && resultA == -1) {
                int mid = (lowerA + upperA) / 2;

                if (nums[lowerA] < target && nums[lowerA + 1] == target) {
                    resultA = lowerA + 1;
                    break;
                }

                if (nums[mid] < target && nums[mid + 1] == target) {
                    resultA = mid + 1;
                    break;
                }

                if (target > nums[mid]) {
                    lowerA = mid + 1;
                } else {
                    upperA = mid;
                }
            }

            int lowerB = found;
            int upperB = upper;

            while (lowerB < upperB && resultB == -1) {
                int mid = (lowerB + upperB) / 2;

                if (nums[upperB] > target && nums[upperB - 1] == target) {
                    resultB = upperB - 1;
                    break;
                }

                if (nums[mid] > target && nums[mid - 1] == target) {
                    resultB = mid - 1;
                    break;
                }

                if (target < nums[mid]) {
                    upperB = mid - 1;
                } else {
                    lowerB = mid;
                }
            }

            if (resultB == -1) {
                resultB = resultA;
            } else if (resultA == -1) {
                resultA = resultB;
            }

            return new int[] { resultA, resultB };
        }
    }
}
