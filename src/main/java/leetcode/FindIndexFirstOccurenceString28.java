package leetcode;

public class FindIndexFirstOccurenceString28 {

    class NativeSolution {

        public int strStr(String haystack, String needle) {
            for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
                if (haystack.charAt(i) == needle.charAt(0)) {
                    boolean found = true;
                    if (needle.length() > 1) {
                        for (int j = 1; j < needle.length(); j++) {
                            if (haystack.charAt(i + j) != needle.charAt(j)) {
                                found = false;
                                break;
                            }
                        }
                    }
                    if (found) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }

    class BuiltInSolution {

        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }
}
