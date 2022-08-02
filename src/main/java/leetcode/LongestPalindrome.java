package leetcode;

public class LongestPalindrome {

  public static void main(String[] args) {
    Solution solution = new Solution();

    System.out.println(solution.longestPalindrome("pwwkew"));
  }

  static class Solution {

    public String longestPalindrome(String s) {
      String longest;

      for (int i = 0; i < s.length() / 2; i++) {
        char pivot = s.charAt(i);
        boolean isFinished = false;
        String center = String.valueOf(pivot);
        for (int j = 1; j < s.length() / 4 && !isFinished; j++) {
          char rhs = s.charAt(i + j);
          center = center + rhs;
          //                    lhsIndex = i - j

        }
      }
      return null;
    }

    public boolean isPalindrome(String s) {
      for (int i = 0; i < s.length() / 2; i++) {
        if (!(s.charAt(i) == s.charAt(s.length() - i - 1))) {
          return false;
        }
      }
      return true;
    }
  }
}
