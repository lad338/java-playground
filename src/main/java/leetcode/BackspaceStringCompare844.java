package leetcode;

import java.util.*;

public class BackspaceStringCompare844 {

    class Solution {

        public boolean backspaceCompare(String s, String t) {
            int indexS = s.length() - 1;
            int indexT = t.length() - 1;

            while (indexS >= 0 || indexT >= 0) {
                indexS = getNextValidCharacterIndex(s, indexS);
                indexT = getNextValidCharacterIndex(t, indexT);

                if (indexS < 0 && indexT < 0) {
                    return true;
                }
                if (indexS < 0 || indexT < 0) {
                    return false;
                }
                if (s.charAt(indexS) != t.charAt(indexT)) {
                    return false;
                }
                indexS--;
                indexT--;
            }
            return true;
        }

        private int getNextValidCharacterIndex(String s, int i) {
            int backspace = 0;
            while (i >= 0) {
                if (backspace == 0 && s.charAt(i) != '#') {
                    break;
                } else if (s.charAt(i) == '#') {
                    backspace++;
                } else if (backspace > 0) {
                    backspace--;
                }

                i--;
            }
            return i;
        }
    }

    class StackSolution {

        public boolean backspaceCompare(String s, String t) {
            Deque<Character> stackS = new ArrayDeque<>();
            Deque<Character> stackT = new ArrayDeque<>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#') {
                    if (stackS.size() > 0) {
                        stackS.pop();
                    }
                } else {
                    stackS.push(s.charAt(i));
                }
            }

            for (int i = 0; i < t.length(); i++) {
                if (t.charAt(i) == '#') {
                    if (stackT.size() > 0) {
                        stackT.pop();
                    }
                } else {
                    stackT.push(t.charAt(i));
                }
            }

            if (stackS.size() != stackT.size()) {
                return false;
            }

            while (stackS.size() > 0) {
                if (!stackS.pop().equals(stackT.pop())) {
                    return false;
                }
            }

            return true;
        }
    }
}
