package leetcode;

import java.util.*;

public class ReconstructItinerary332 {

    class Solution {

        Map<String, PriorityQueue<String>> dict = new HashMap<>();
        List<String> ans = new ArrayList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            for (List<String> ticket : tickets) {
                dict.putIfAbsent(ticket.get(0), new PriorityQueue<>());
                dict.get(ticket.get(0)).add(ticket.get(1));
            }

            dfs("JFK");
            Collections.reverse(ans);
            return ans;
        }

        public void dfs(String source) {
            while (dict.get(source) != null && !dict.get(source).isEmpty()) {
                dfs(dict.get(source).poll());
            }
            ans.add(source);
        }
    }
}
