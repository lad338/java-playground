package leetcode;

import java.util.*;

public class IntToRoman12 {

  class Solution {

    public String intToRoman(int num) {
      Map<Character, Integer> valueMap = new HashMap<>() {
        {
          put('I', 1);
          put('V', 5);
          put('X', 10);
          put('L', 50);
          put('C', 100);
          put('D', 500);
          put('M', 1000);
        }
      };

      List<Character> inverseValueList = Arrays.asList('M', 'D', 'C', 'L', 'X', 'V', 'I');

      StringBuilder result = new StringBuilder();
      for (char currentChar : inverseValueList) {
        int currentValue = valueMap.get(currentChar);
        int current = num / currentValue;
        for (int j = 0; j < current; j++) {
          result.append(currentChar);
        }
        num = num % currentValue;
        if (currentChar == 'M' && num / 100 == 9) {
          num -= 900;
          result.append("CM");
        }
        if (currentChar == 'M' && num / 100 == 4) {
          num -= 400;
          result.append("CD");
        }
        if (currentChar == 'C' && num / 10 == 9) {
          num -= 90;
          result.append("XC");
        }
        if (currentChar == 'C' && num / 10 == 4) {
          num -= 40;
          result.append("XL");
        }
        if (currentChar == 'X' && num == 9) {
          num -= 9;
          result.append("IX");
        }
        if (currentChar == 'X' && num == 4) {
          num -= 4;
          result.append("IV");
        }
      }
      return result.toString();
    }
  }

  class Token49Solution {

    public String intToRoman(int num) {
      Map<String, Integer> valueMap = new HashMap<>() {
        {
          put("I", 1);
          put("IV", 4);
          put("V", 5);
          put("IX", 9);
          put("X", 10);
          put("XL", 40);
          put("L", 50);
          put("XC", 90);
          put("C", 100);
          put("CD", 400);
          put("D", 500);
          put("CM", 900);
          put("M", 1000);
        }
      };

      List<String> inverseValueList = Arrays.asList(
        "M",
        "CM",
        "D",
        "CD",
        "C",
        "XC",
        "L",
        "XL",
        "X",
        "IX",
        "V",
        "IV",
        "I"
      );

      StringBuilder result = new StringBuilder();
      for (String currentChar : inverseValueList) {
        int currentValue = valueMap.get(currentChar);
        int current = num / currentValue;
        result.append(String.valueOf(currentChar).repeat(Math.max(0, current)));
        num = num % currentValue;
      }
      return result.toString();
    }
  }

  class Token49MinusSolution {

    public String intToRoman(int num) {
      Map<String, Integer> valueMap = new HashMap<>() {
        {
          put("I", 1);
          put("IV", 4);
          put("V", 5);
          put("IX", 9);
          put("X", 10);
          put("XL", 40);
          put("L", 50);
          put("XC", 90);
          put("C", 100);
          put("CD", 400);
          put("D", 500);
          put("CM", 900);
          put("M", 1000);
        }
      };

      List<String> inverseValueList = Arrays.asList(
        "M",
        "CM",
        "D",
        "CD",
        "C",
        "XC",
        "L",
        "XL",
        "X",
        "IX",
        "V",
        "IV",
        "I"
      );

      StringBuilder result = new StringBuilder();
      for (String currentChar : inverseValueList) {
        int currentValue = valueMap.get(currentChar);
        while (num >= currentValue) {
          result.append(currentChar);
          num -= currentValue;
        }
      }
      return result.toString();
    }
  }

  class LinkedHashMapSolution {

    public String intToRoman(int num) {
      Map<String, Integer> valueMap = new LinkedHashMap<>() {
        {
          put("M", 1000);
          put("CM", 900);
          put("D", 500);
          put("CD", 400);
          put("C", 100);
          put("XC", 90);
          put("L", 50);
          put("XL", 40);
          put("X", 10);
          put("IX", 9);
          put("V", 5);
          put("IV", 4);
          put("I", 1);
        }
      };

      StringBuilder result = new StringBuilder();
      for (Map.Entry<String, Integer> entry : valueMap.entrySet()) {
        int currentValue = entry.getValue();
        while (num >= currentValue) {
          result.append(entry.getKey());
          num -= currentValue;
        }
      }
      return result.toString();
    }
  }
}
