package leetcode;

public class PalindromeNumber {

  static class Solution {

    public static boolean isPalindrome(int x) {
      if (x < 0) {
        return false;
      }
      int length = (int) (Math.log10(x) + 1);
      if (length == 1) {
        return true;
      }
      for (int i = 0; i < length / 2; i++) {
        int lhs = x / (int) Math.pow(10, length - i - 1) % 10;
        int rhs = x / (int) Math.pow(10, i) % 10;
        if (lhs != rhs) {
          return false;
        }
      }
      return true;
    }
  }

  static class Solution2 {

    public static boolean isPalindrome(int x) {
      if (x < 0 || (x % 10 == 0 && x != 0)) {
        return false;
      }

      int revertingNumber = 0;
      while (x > revertingNumber) {
        revertingNumber = revertingNumber * 10 + x % 10;
        x /= 10;
      }
      return x == revertingNumber || x == revertingNumber / 10;
    }
  }

  public static void main(String[] args) {
    System.out.println(Solution2.isPalindrome(1001));
  }
}
