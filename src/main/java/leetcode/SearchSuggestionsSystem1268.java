package leetcode;

import java.util.*;

public class SearchSuggestionsSystem1268 {

    class Solution {

        public List<List<String>> suggestedProducts(
            String[] products,
            String searchWord
        ) {
            Arrays.sort(products);

            int L = 0;
            int R = products.length - 1;

            List<List<String>> result = new ArrayList<>();

            for (int i = 0; i < searchWord.length(); i++) {
                char requiredCharacter = searchWord.charAt(i);

                while (
                    L <= R &&
                    (
                        products[L].length() <= i ||
                        products[L].charAt(i) != requiredCharacter
                    )
                ) {
                    L++;
                }

                while (
                    L <= R &&
                    (
                        products[R].length() <= i ||
                        products[R].charAt(i) != requiredCharacter
                    )
                ) {
                    R--;
                }

                List<String> currentResult = new ArrayList<>();
                for (int j = L; j <= Math.min(R, L + 2); j++) {
                    currentResult.add(products[j]);
                }
                result.add(currentResult);
            }

            return result;
        }
    }
}
