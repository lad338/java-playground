package interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Wordle {

    private static String checkGuess(String guess, String word) {
        word = word.toLowerCase();
        guess = guess.toLowerCase();

        // Assume that guess and word are in the same number characters

        // TODO correct char in correct position - G
        // TODO correct char in wrong position - Y
        // TODO others - _

        char[] result = new char[guess.length()];
        Arrays.fill(result, '_');

        // APPLE ZZZAA
        // ___Y_ / ___YY

        Map<Character, Integer> map = new HashMap<>();

        //Upload all word characters count to map first
        for (char c : word.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);

            if (c == word.charAt(i)) {
                result[i] = 'G';
                map.put(c, map.get(c) - 1);
            } else if (map.getOrDefault(c, 0) >= 1) {
                result[i] = 'Y';
                map.put(c, map.get(c) - 1);
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(checkGuess("ZZZAA", "APPLE"));
        System.out.println(checkGuess("ZZZPP", "APPLE"));
        System.out.println(checkGuess("APALE", "APPLE"));
        System.out.println(checkGuess("APPLE", "APPLE"));
    }
}
