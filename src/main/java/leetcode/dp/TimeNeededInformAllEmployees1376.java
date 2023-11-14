package leetcode.dp;

public class TimeNeededInformAllEmployees1376 {

    class Solution {

        int[] cache;
        int[] manager;
        int[] informTime;

        public int numOfMinutes(
            int n,
            int headID,
            int[] manager,
            int[] informTime
        ) {
            this.manager = manager;
            this.informTime = informTime;
            this.cache = new int[n];

            cache[headID] = informTime[headID];
            for (int i = 0; i < n; i++) {
                helper(i);
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, cache[i]);
            }
            return result;
        }

        private int helper(int index) {
            if (informTime[index] == 0) {
                return 0;
            }

            if (cache[index] != 0) {
                return cache[index];
            }

            int result = helper(manager[index]) + informTime[index];
            cache[index] = result;
            return result;
        }
    }
}
