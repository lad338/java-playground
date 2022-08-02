package leetcode;

public class StringToIntegerAtoi8 {

  static class Solution {

    public int myAtoi(String s) {
      StringBuilder sb = new StringBuilder();

      boolean isDone = false;
      boolean isReadingDigit = false;
      boolean hasPrefix = false;

      for (int i = 0; i < s.length() && !isDone; i++) {
        char current = s.charAt(i);
        if (current == ' ') {
          if (isReadingDigit) {
            isDone = true;
          }
          continue;
        }
        if (current == '+' || current == '-') {
          if (hasPrefix || isReadingDigit) {
            isDone = true;
            continue;
          }
          sb.append(current);
          hasPrefix = true;
          isReadingDigit = true;
          continue;
        }
        if (Character.isDigit(current)) {
          if (!isReadingDigit) {
            isReadingDigit = true;
          }
          sb.append(current);
          continue;
        }
        isDone = true;
      }

      String resultS = sb.toString();
      if (resultS.length() == 0 || !Character.isDigit(resultS.charAt(resultS.length() - 1))) {
        return 0;
      }

      try {
        return Integer.parseInt(resultS);
      } catch (Exception e) {
        if (resultS.charAt(0) == '-') {
          return Integer.MIN_VALUE;
        } else {
          return Integer.MAX_VALUE;
        }
      }
    }
  }
}
