package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateParentheses22 {

    class Solution {

        public List<String> generateParenthesis(int n) {
            return generateParenthesis(
                new ArrayList<>() {
                    {
                        add("");
                    }
                },
                n,
                0
            );
        }

        public List<String> generateParenthesis(
            List<String> generated,
            int n,
            int opened
        ) {
            if (n == 0 && opened == 0) {
                return generated;
            }

            List<String> closingList = new ArrayList<>();
            List<String> openingList = new ArrayList<>();
            List<String> resultList = new ArrayList<>();

            if (opened > 0) {
                closingList =
                    generated.stream().map(it -> it.concat(")")).toList();

                closingList = generateParenthesis(closingList, n, opened - 1);
            }

            if (n > 0) {
                openingList =
                    generated.stream().map(it -> it.concat("(")).toList();

                openingList =
                    generateParenthesis(openingList, n - 1, opened + 1);
            }

            resultList.addAll(closingList);
            resultList.addAll(openingList);

            return resultList;
        }
    }
}
