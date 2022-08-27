package leetcode;

public class GasStation134 {

    class Solution {

        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int sum = 0;
            int start = 0;
            int gasSum = 0;
            for (int i = 0; i < n; i++) {
                int netGas = gas[i] - cost[i];
                sum += netGas;
                gasSum += (netGas);
                if (gasSum < 0) {
                    start = i + 1;
                    gasSum = 0;
                }
            }
            return sum >= 0 ? start : -1;
        }
    }
}
