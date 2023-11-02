package leetcode;

public class ChampaignTower799 {

    class Solution {

        public double champagneTower(
            int poured,
            int query_row,
            int query_glass
        ) {
            double[] previousRow = new double[] { poured };
            for (int i = 1; i <= query_row; i++) {
                double[] currentRow = new double[i + 1];
                for (int j = 0; j < i; j++) {
                    double extra = Math.max(0, (previousRow[j] - 1) * 0.5);
                    currentRow[j] += extra;
                    currentRow[j + 1] += extra;
                }
                previousRow = currentRow;
            }
            return Math.min(1, previousRow[query_glass]);
        }
    }
}
