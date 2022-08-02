package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses20 {

  static class Solution {

    public boolean isValid(String s) {
      Deque<Character> stack = new ArrayDeque<>();
      for (int i = 0; i < s.length(); i++) {
        char current = s.charAt(i);
        if (current == '(' || current == '{' || current == '[') {
          stack.push(current);
        }
        if (stack.size() == 0) {
          return false;
        }

        if (current == ')') {
          if (!stack.pop().equals('(')) {
            return false;
          }
        }
        if (current == '}') {
          if (!stack.pop().equals('{')) {
            return false;
          }
        }
        if (current == ']') {
          if (!stack.pop().equals('[')) {
            return false;
          }
        }
      }
      return stack.size() == 0;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isValid("([)]"));
  }
}
