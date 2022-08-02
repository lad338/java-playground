package hackerrank.datastructure.array;

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

//https://www.hackerrank.com/challenges/dynamic-array/problem
public class DynamicArray {

  static class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
      // Write your code here

      List<Integer> result = new ArrayList<>();
      Map<Integer, List<Integer>> arr = new HashMap<>();

      List<Integer> baseList = new ArrayList<>();
      baseList.add(queries.get(0).get(1));
      arr.put(0, baseList);

      int lastAnswer = 0;

      for (int i = 1; i <= queries.get(0).get(1); i++) {
        List<Integer> currentQ = queries.get(i);
        int idx = (currentQ.get(1) ^ lastAnswer) % n;
        int y = currentQ.get(2);
        if (currentQ.get(0) == 1) {
          List<Integer> list = arr.get(idx);
          if (list == null) {
            list = new ArrayList<>();
          }
          list.add(y);
          arr.put(idx, list);
        } else {
          int requiredIndex = y % arr.get(idx).size();
          lastAnswer = arr.get(idx).get(requiredIndex);
          result.add(lastAnswer);
        }
      }
      return result;
    }
  }

  public static class Solution {

    public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH"))
      );

      String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

      int n = Integer.parseInt(firstMultipleInput[0]);

      int q = Integer.parseInt(firstMultipleInput[1]);

      List<List<Integer>> queries = new ArrayList<>();

      IntStream
        .range(0, q)
        .forEach(i -> {
          try {
            queries.add(
              Stream
                .of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
            );
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

      List<Integer> result = Result.dynamicArray(n, queries);

      bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

      bufferedReader.close();
      bufferedWriter.close();
    }
  }
}
