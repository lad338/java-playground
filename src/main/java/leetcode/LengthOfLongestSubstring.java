package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LengthOfLongestSubstring {

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("pwwkew"));
  }

  public static int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) {
      return 0;
    }

    int result = 1;
    List<Character> previousCharacters = new ArrayList<>();

    //TODO
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      //            System.out.println(i + ": " + currentChar);
      //            System.out.println("pre  | " + previousCharacters);
      if (previousCharacters.contains(currentChar)) {
        if (result < previousCharacters.size()) {
          result = previousCharacters.size();
        }
        previousCharacters =
          previousCharacters.subList(
            previousCharacters.indexOf(currentChar) + 1,
            previousCharacters.size()
          );
      }
      //            System.out.println("post | " + previousCharacters);
      previousCharacters.add(currentChar);
      //            System.out.println("final| " + previousCharacters);

    }

    return Math.max(previousCharacters.size(), result);
  }
}
