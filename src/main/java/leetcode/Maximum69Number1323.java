package leetcode;

public class Maximum69Number1323 {

    class StringSolution {

        public int maximum69Number(int num) {
            String s = Integer.toString(num);
            int index = s.indexOf("6");
            if (index >= 0) {
                return (
                    num + ((int) (Math.pow(10, (s.length() - index - 1)))) * 3
                );
            }
            return num;
        }
    }

    class BuiltinFunctionSolution {

        public int maximum69Number(int num) {
            String s = Integer.toString(num);
            return Integer.parseInt(s.replaceFirst("6", "9"));
        }
    }

    class NumberOnlySolution {

        public int maximum69Number(int num) {
            int base = num;
            int max = -1;
            int counter = 0;
            while (base > 0) {
                int remainder = base % 10;
                if (remainder == 6) {
                    max = counter;
                }
                base /= 10;
                counter++;
            }
            if (max >= 0) {
                return num + ((int) Math.pow(10, max)) * 3;
            }
            return num;
        }
    }
}
