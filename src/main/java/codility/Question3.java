package codility;

public class Question3 {

    class Solution {

        public int solution(int[] A, int X, int Y, int Z) {
            int car = 0;
            int time = 0;

            int carX = 0, carY = 0, carZ = 0;

            while (car < A.length) {
                boolean findNext = true;
                for (int i = car; i < A.length && findNext; i++) {
                    findNext = false;
                    if (carX == 0 && X >= A[i]) {
                        carX = A[i];
                        car++;
                        findNext = true;
                    } else if (carY == 0 && Y >= A[i]) {
                        carY = A[i];
                        car++;
                        findNext = true;
                    } else if (carZ == 0 && Z >= A[i]) {
                        carZ = A[i];
                        car++;
                        findNext = true;
                    } else if (X < A[i] && Y < A[i] && Z < A[i]) {
                        return -1;
                    }
                }

                if (carX > 0) {
                    carX--;
                    X--;
                }
                if (carY > 0) {
                    carY--;
                    Y--;
                }
                if (carZ > 0) {
                    carZ--;
                    Z--;
                }

                time++;
            }

            return time - 1;
        }
    }
}
