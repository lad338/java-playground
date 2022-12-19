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
        String numberRef = predictNumber(15);
        for (int i = 0; i < 1000; i++) {
            int predicted = predictDigit(i);
            int correct = Integer.parseInt("" +numberRef.charAt(i));

            if ( correct == predicted ) {
                System.out.println(i +" th digit is correct as " + correct );
            } else {
                System.out.println(i +" th digit is wrong, correct: " + correct + ", wrong: " + predicted);
            }
        }
    }

    public static int predictDigit(int n) {
        if (n == 0) return 0;

        int pow2Ceil = (int)Math.ceil(Math.log(n)/Math.log(2));
        if (n == Math.pow(2, pow2Ceil)) return 1;

        return (1 + predictDigit(n - (int)Math.pow(2, pow2Ceil - 1 ))) % 3;
    }

}
