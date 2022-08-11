package interview;

import java.io.*;

public class ReadLinesAndReverseSomeWords {

    static class myCode {

        public static void main(String[] args) throws java.lang.Exception {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
            );
            // added a trim here because the test case have a space after 6
            // read number
            int number = Integer.parseInt(br.readLine().trim());

            // read for (number) lines
            for (int i = 0; i < number; i++) {
                // read current line
                String line = br.readLine();
                // split by space
                String[] words = line.split(" ");
                // string buffer for the output line
                StringBuilder result = new StringBuilder();
                // for each index, place the word in the required order
                for (int j = 0; j < words.length; j++) {
                    if (j == 0 || j == words.length - 1) {
                        result.append(words[j]);
                    } else {
                        result.append(words[words.length - 1 - j]);
                    }
                    // add space if not the final character
                    if (j != words.length - 1) {
                        result.append(" ");
                    }
                }
                // print the required line
                System.out.println(result);
            }
        }
    }
}
