package hackerrank.amazon.oa.demo;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

class Result {

    /*
     * Complete the 'countGroups' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY related as parameter.
     */

    public static int countGroups(List<String> related) {
        // Write your code here

        Map<Integer, Set<Integer>> relations = new HashMap<>();

        int person = 0;
        for (String s : related) {
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current == '1') {
                    Set<Integer> relatedList = relations.getOrDefault(
                        person,
                        new HashSet<>()
                    );
                    relatedList.add(i);
                    relations.put(person, relatedList);
                }
            }
            person++;
        }

        int result = 0;
        Set<Integer> searched = new HashSet<>();

        for (int i = 0; i < related.size(); i++) {
            if (!searched.contains(i)) {
                recursive(i, searched, relations);
                result++;
            }
        }

        return result;
    }

    public static void recursive(
        int current,
        Set<Integer> searched,
        Map<Integer, Set<Integer>> relations
    ) {
        if (searched.contains(current)) {
            return;
        }

        searched.add(current);
        Set<Integer> toSearch = new HashSet<>(
            relations.getOrDefault(current, Collections.emptySet())
        );

        for (Integer i : searched) {
            toSearch.remove(i);
        }

        for (Integer i : toSearch) {
            recursive(i, searched, relations);
        }
    }
}

public class GiftingGroups {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );
        BufferedWriter bufferedWriter = new BufferedWriter(
            new FileWriter(System.getenv("OUTPUT_PATH"))
        );

        int relatedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> related = IntStream
            .range(0, relatedCount)
            .mapToObj(i -> {
                try {
                    return bufferedReader.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            })
            .collect(toList());

        int result = Result.countGroups(related);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
