package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PascalTriangle {

    static class Solution {

        public List<List<Integer>> generate(int numRows) {
            final List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < numRows; i++) {
                if (i == 0) {
                    result.add(Collections.singletonList(1));
                } else {
                    final List<Integer> previousList = result.get(i - 1);
                    final List<Integer> newList = IntStream
                        .range(0, previousList.size())
                        .map(it -> {
                            if (it == 0) {
                                return 1;
                            } else {
                                return (
                                    previousList.get(it - 1) +
                                    previousList.get(it)
                                );
                            }
                        })
                        .boxed()
                        .collect(Collectors.toList());
                    newList.add(1);
                    result.add(newList);
                }
            }
            return result;
        }
    }
}
