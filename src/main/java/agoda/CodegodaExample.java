package agoda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CodegodaExample {

    class Solution {

        public static final List<String> digits = Arrays.asList(
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        );

        public static final Map<String, Integer> digitsMap = new HashMap<>() {
            {
                put("zero", 0);
                put("one", 1);
                put("two", 2);
                put("three", 3);
                put("four", 4);
                put("five", 5);
                put("six", 6);
                put("seven", 7);
                put("eight", 8);
                put("nine", 9);
            }
        };

        public static void main(String[] args) {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
            );
            List<String> inputs;
            try {
                final int n = Integer.parseInt(br.readLine());
                inputs = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    inputs.add(br.readLine());
                }
                System.out.println(inputs);
                System.out.println(inputs.size());

                Map<String, Map<Character, Integer>> digitCharacterCount = new HashMap<>();
                for (String digit : digits) {
                    Map<Character, Integer> characterCount = new HashMap<>();
                    for (int i = 0; i < digit.length(); i++) {
                        characterCount.put(
                            digit.charAt(i),
                            characterCount.getOrDefault(digit.charAt(i), 0) + 1
                        );
                    }
                    digitCharacterCount.put(digit, characterCount);
                }

                for (String input : inputs) {
                    Map<Character, Integer> inputCharacterCount = new HashMap<>();
                    for (int i = 0; i < input.length(); i++) {
                        inputCharacterCount.put(
                            input.charAt(i),
                            inputCharacterCount.getOrDefault(
                                input.charAt(i),
                                0
                            ) +
                            1
                        );
                    }

                    Map<String, Integer> digitCount = new HashMap<>();
                    for (String digit : digits) {
                        final int count = checkCharacters(
                            inputCharacterCount,
                            digitCharacterCount.get(digit)
                        );

                        digitCount.put(digit, count);
                    }
                    String smallestNonZero = "zero";
                    for (int i = 1; i <= 9; i++) {
                        if (digitCount.get(digits.get(i)) > 0) {
                            smallestNonZero = digits.get(i);
                            break;
                        }
                    }
                    if (smallestNonZero.equals("zero")) {
                        System.out.println(0);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(digitsMap.get(smallestNonZero));
                        digitCount.put(
                            smallestNonZero,
                            digitCount.get(smallestNonZero) - 1
                        );
                        for (int i = 0; i < 10; i++) {
                            String current = digits.get(i);
                            final Integer count = digitCount.getOrDefault(
                                current,
                                0
                            );
                            for (int j = 0; j < count; j++) {
                                sb.append(digitsMap.get(current));
                            }
                        }
                        System.out.println(Long.parseLong(sb.toString()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        }

        public static int checkCharacters(
            Map<Character, Integer> input,
            Map<Character, Integer> required
        ) {
            int n = 0;
            final Set<Map.Entry<Character, Integer>> entries = required.entrySet();

            boolean enough = true;

            while (enough) {
                for (Map.Entry<Character, Integer> entry : entries) {
                    if (
                        !(
                            input.getOrDefault(entry.getKey(), 0) >=
                            entry.getValue()
                        )
                    ) {
                        enough = false;
                        break;
                    }
                }
                if (enough) {
                    n++;
                    for (Map.Entry<Character, Integer> entry : entries) {
                        input.put(
                            entry.getKey(),
                            input.get(entry.getKey()) - entry.getValue()
                        );
                    }
                }
            }
            return n;
        }
    }
}
