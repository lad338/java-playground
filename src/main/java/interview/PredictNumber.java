package interview;

/*
Suppose I have the following sequence:
0 -> 01 -> 0112 -> 01121220 -> 0112122012202001 -> ...

Can you write a program to find the value of the n th number?

 */


public class PredictNumber {

    // Assuming n starts from 0
    public static String predictNumber(int n) {
        if (n <= 0) {
            return "0";
        }

        final double digits = Math.pow(2, n);

        final int[] digitList = new int[(int)digits + 1];
        digitList[0] = 0;

        final StringBuilder sb = new StringBuilder();
        sb.append(0);

        int copyIndex = 0;
        int counter = 0;
        for (int i = 1; i < digits; i++) {
            if (copyIndex >= Math.pow(2, counter)) {
                counter++;
                copyIndex = 0;
            }
            digitList[i] = (digitList[copyIndex] + 1) % 3;
            sb.append(digitList[i]);
            copyIndex++;
        }

        return sb.toString();
    }

    public static void main (String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(predictNumber(i));
        }
    }
}
