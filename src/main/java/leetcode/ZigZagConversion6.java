package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion6 {

    static class Solution {

        public String convert(String s, int numRows) {
            if (numRows == 1) {
                return s;
            }

            List<List<Character>> result = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                result.add(new ArrayList<>());
            }
            boolean isColumn = true;
            int currentRow = 0;
            for (int i = 0; i < s.length(); i++) {
                if (isColumn || numRows == 2) {
                    result.get(currentRow).add(s.charAt(i));
                    currentRow += 1;
                    if (currentRow == numRows) {
                        isColumn = false;
                    }
                    if (numRows == 2 && currentRow == 2) {
                        currentRow = 0;
                    }
                } else {
                    currentRow -= 1;
                    for (int j = 0; j < currentRow - 1; j++) {
                        result.get(j).add(null);
                    }
                    result.get(currentRow - 1).add(s.charAt(i));
                    for (int j = currentRow + 1; j < numRows; j++) {
                        result.get(j).add(null);
                    }
                    if (currentRow == 1) {
                        isColumn = true;
                    }
                }
            }

            StringBuilder resultString = new StringBuilder();
            for (List<Character> characters : result) {
                for (Character character : characters) {
                    if (character != null) {
                        resultString.append(character);
                    }
                }
            }
            return resultString.toString();
        }
    }

    static class Solution2 {

        public String convert(String s, int numRows) {
            if (numRows == 1) {
                return s;
            }
            StringBuilder result = new StringBuilder();
            int groupSize = numRows * 2 - 2;
            int finalGroupSize = s.length() % groupSize;
            int numGroups = finalGroupSize == 0
                ? s.length() / groupSize
                : s.length() / groupSize + 1;

            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                for (int groupIndex = 0; groupIndex < numGroups; groupIndex++) {
                    int columnCharacterIndex =
                        rowIndex + groupIndex * groupSize;
                    if (columnCharacterIndex < s.length()) {
                        result.append(s.charAt(columnCharacterIndex));
                    }
                    int interColumnCharacterIndex =
                        (groupSize - rowIndex) + groupIndex * groupSize;

                    if (
                        rowIndex != 0 &&
                        rowIndex != numRows - 1 &&
                        interColumnCharacterIndex < s.length()
                    ) {
                        result.append(s.charAt(interColumnCharacterIndex));
                    }
                }
            }

            return result.toString();
        }
    }
    /*
     0     6
     1  5  7 11
     2  4  8 10
     3     9

     */
}
