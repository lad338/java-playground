package leetcode;

import java.util.*;

public class WordLadder127 {

    class Solution {

        public int ladderLength(
            String beginWord,
            String endWord,
            List<String> wordList
        ) {
            List<String> list = new ArrayList<>();
            Set<String> set = new HashSet<>(wordList);
            if (!set.contains(endWord)) {
                return 0;
            }
            if (beginWord.equals(endWord)) {
                return 1;
            }

            int count = 1;
            list.add(beginWord);

            while (list.size() > 0) {
                List<String> checkList = new ArrayList<>(list);
                list.clear();
                count++;
                for (String current : checkList) {
                    for (int i = 0; i < current.length(); i++) {
                        for (int c = 'a'; c <= 'z'; c++) {
                            char[] currentArray = current.toCharArray();
                            currentArray[i] = (char) c;
                            String newWord = new String(currentArray);
                            if (!set.contains(newWord)) {
                                continue;
                            }
                            if (newWord.equals(endWord)) {
                                return count;
                            }
                            set.remove(newWord);
                            list.add(newWord);
                        }
                    }
                }
            }

            return 0;
        }
    }
}
