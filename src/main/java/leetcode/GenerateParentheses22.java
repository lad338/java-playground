package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateParentheses22 {

    class Solution {

        public List<String> generateParenthesis(int n) {
            return generateParenthesis(Collections.singletonList(""), n, 0);
        }

        public List<String> generateParenthesis(
            List<String> generated,
            int n,
            int opened
        ) {
            if (n == 0 && opened == 0) {
                return generated;
            }

            List<String> resultList = new ArrayList<>();

            if (opened > 0) {
                final List<String> closingList = generated
                    .stream()
                    .map(it -> it.concat(")"))
                    .toList();

                final List<String> closedList = generateParenthesis(
                    closingList,
                    n,
                    opened - 1
                );

                resultList.addAll(closedList);
            }

            if (n > 0) {
                final List<String> openingList = generated
                    .stream()
                    .map(it -> it.concat("("))
                    .toList();

                final List<String> openedList = generateParenthesis(
                    openingList,
                    n - 1,
                    opened + 1
                );

                resultList.addAll(openedList);
            }

            return resultList;
        }
    }
}
