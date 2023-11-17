package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MedianOf2SortedArrays4 {

    class Solution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length;

            int length2 = nums2.length;

            if (length1 > length2) {
                return findMedianSortedArrays(nums2, nums1);
            }

            if (length1 <= 2) {
                return handleBaseCase(nums1, nums2);
            }

            double mid1 = getMedian(nums1, length1);
            double mid2 = getMedian(nums2, length2);

            int remove = length1 % 2 == 1 ? length1 / 2 : length1 / 2 - 1;

            if (mid1 > mid2) {
                return findMedianSortedArrays(
                    Arrays.copyOfRange(nums1, 0, length1 - remove),
                    Arrays.copyOfRange(nums2, remove, length2)
                );
            } else {
                // remove top of nums2 and bottom of nums1
                return findMedianSortedArrays(
                    Arrays.copyOfRange(nums1, remove, length1),
                    Arrays.copyOfRange(nums2, 0, length2 - remove)
                );
            }
        }

        public double getMedian(int[] nums, int length) {
            if (length % 2 == 0) {
                return (nums[length / 2 - 1] + nums[length / 2]) / 2.0;
            } else {
                return nums[length / 2];
            }
        }

        public double handleBaseCase(int[] nums1, int[] nums2) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            //should not occur
            if (length1 > 2) {
                return findMedianSortedArrays(nums1, nums2);
            }
            List<Integer> numList = new ArrayList<>();
            if (length2 % 2 == 1) {
                if (length2 > 3) {
                    numList.add(nums2[length2 / 2 - 1]);
                    numList.add(nums2[length2 / 2]);
                    numList.add(nums2[length2 / 2 + 1]);
                } else {
                    numList.addAll(
                        Arrays
                            .stream(nums2)
                            .boxed()
                            .collect(Collectors.toCollection(ArrayList::new))
                    );
                }
            } else {
                if (length2 > 4) {
                    numList.add(nums2[length2 / 2 - 2]);
                    numList.add(nums2[length2 / 2 - 1]);
                    numList.add(nums2[length2 / 2]);
                    numList.add(nums2[length2 / 2 + 1]);
                } else {
                    numList.addAll(
                        Arrays
                            .stream(nums2)
                            .boxed()
                            .collect(Collectors.toCollection(ArrayList::new))
                    );
                }
            }

            for (int n : nums1) {
                numList.add(findInsertLocation(numList, n), n);
            }

            int length = numList.size();

            if (length % 2 == 1) {
                return numList.get(length / 2);
            }
            return (
                (numList.get(length / 2 - 1) + numList.get(length / 2)) / 2.0
            );
        }

        public int findInsertLocation(List<Integer> nums, int target) {
            int length = nums.size();
            if (length == 0) {
                return 0;
            }

            if (length == 1) {
                if (nums.get(0) < target) {
                    return 1;
                } else {
                    return 0;
                }
            }

            int lhs = nums.get(length / 2 - 1);
            int rhs = nums.get(length / 2);

            if (lhs <= target && rhs >= target) {
                return length / 2;
            } else if (target <= lhs) {
                return findInsertLocation(nums.subList(0, length / 2), target);
            } else {
                return (
                    length /
                    2 +
                    findInsertLocation(nums.subList(length / 2, length), target)
                );
            }
        }
    }

    class JavaSolution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            List<Integer> numList = Arrays
                .stream(nums1)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
            numList.addAll(
                Arrays
                    .stream(nums2)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new))
            );
            Collections.sort(numList);
            int length = numList.size();
            if (length % 2 == 1) {
                return numList.get(length / 2);
            }
            return (
                (numList.get(length / 2 - 1) + numList.get(length / 2)) / 2.0
            );
        }
    }

    class NeetcodeSolution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int fullLength = nums1.length + nums2.length;
            int halfLength = fullLength / 2;

            int L = 0;
            int R = nums1.length - 1;
            while (true) {
                int M1 = (int) Math.floor((L + (R - L) / 2.0f));
                int M2 = halfLength - M1 - 2;

                int left1 = M1 >= 0 ? nums1[M1] : Integer.MIN_VALUE;
                int right1 = M1 + 1 < nums1.length
                    ? nums1[M1 + 1]
                    : Integer.MAX_VALUE;

                int left2 = M2 >= 0 ? nums2[M2] : Integer.MIN_VALUE;
                int right2 = M2 + 1 < nums2.length
                    ? nums2[M2 + 1]
                    : Integer.MAX_VALUE;

                if (right2 >= left1 && right1 >= left2) {
                    if (fullLength % 2 == 1) {
                        return Math.min(right1, right2);
                    } else {
                        return (
                            (
                                Math.min(right1, right2) +
                                Math.max(left1, left2)
                            ) /
                            2.0f
                        );
                    }
                } else if (right2 < left1) {
                    R = M1 - 1;
                } else {
                    L = M1 + 1;
                }
            }
        }
    }
}
