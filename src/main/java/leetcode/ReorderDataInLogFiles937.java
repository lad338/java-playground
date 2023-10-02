package leetcode;

import java.util.*;

public class ReorderDataInLogFiles937 {

    class Solution {

        public String[] reorderLogFiles(String[] logs) {
            List<String> letterList = new ArrayList<>();
            List<String> digitList = new ArrayList<>();

            for (String s : logs) {
                final int firstSpace = s.indexOf(' ');
                if (Character.isDigit(s.charAt(firstSpace + 1))) {
                    digitList.add(s);
                } else {
                    letterList.add(s);
                }
            }

            letterList.sort((a, b) -> {
                final int aFirstSpace = a.indexOf(' ');
                final String aContent = a.substring(aFirstSpace + 1);

                final int bFirstSpace = b.indexOf(' ');
                final String bContent = b.substring(bFirstSpace + 1);

                if (aContent.compareTo(bContent) != 0) {
                    return aContent.compareTo(bContent);
                }

                final String aKey = a.substring(0, aFirstSpace);
                final String bKey = b.substring(0, bFirstSpace);

                return aKey.compareTo(bKey);
            });

            letterList.addAll(digitList);

            String[] result = new String[logs.length];
            return letterList.toArray(result);
        }
    }
}
