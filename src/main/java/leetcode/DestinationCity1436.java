package leetcode;

import java.util.*;

public class DestinationCity1436 {

    class Solution {

        public String destCity(List<List<String>> paths) {
            Set<String> starts = new HashSet<>();
            Set<String> ends = new HashSet<>();
            for (List<String> path : paths) {
                if (ends.contains(path.get(0))) {
                    ends.remove(path.get(0));
                } else {
                    starts.add(path.get(0));
                }

                if (starts.contains(path.get(1))) {
                    starts.remove(path.get(1));
                } else {
                    ends.add(path.get(1));
                }
            }

            return ends.stream().findFirst().orElseThrow();
        }
    }

    class FasterSolution {

        public String destCity(List<List<String>> paths) {
            Set<String> starts = new HashSet<>();
            for (List<String> path : paths) {
                starts.add(path.get(0));
            }

            for (List<String> path : paths) {
                if (!starts.contains(path.get(1))) {
                    return path.get(1);
                }
            }

            return starts.stream().findFirst().orElseThrow();
        }
    }
}
