package leetcode;

public class FindInMountainArray1095 {

    interface MountainArray {
        int get(int index);

        int length();
    }

    class Solution {

        public int findInMountainArray(int target, MountainArray mountainArr) {
            int length = mountainArr.length();

            int l = 1;
            int r = length - 2;
            int m = l;

            while (l <= r) {
                m = (l + r) / 2;

                int value1 = mountainArr.get(m - 1);
                int value2 = mountainArr.get(m);
                int value3 = mountainArr.get(m + 1);

                if (value1 < value2 && value2 < value3) {
                    l = m + 1;
                } else if (value1 > value2 && value2 > value3) {
                    r = m - 1;
                } else {
                    break;
                }
            }

            l = 0;
            r = m;
            while (l <= r) {
                m = (l + r) / 2;
                int value = mountainArr.get(m);

                if (value == target) {
                    return m;
                }
                if (value < target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            l = m;
            r = length - 1;
            while (l <= r) {
                m = (l + r) / 2;
                int value = mountainArr.get(m);

                if (value == target) {
                    return m;
                }
                if (value > target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            return -1;
        }
    }
}
