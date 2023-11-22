package leetcode;

import java.util.*;

public class Dota2Senate649 {

    class Solution {

        public String predictPartyVictory(String senate) {
            Queue<Integer> radiantActiveQueue = new ArrayDeque<>();
            Queue<Integer> direActiveQueue = new ArrayDeque<>();

            Queue<Integer> radiantWaitingQueue = new ArrayDeque<>();
            Queue<Integer> direWaitingQueue = new ArrayDeque<>();

            char[] senateArr = senate.toCharArray();

            for (int i = 0; i < senate.length(); i++) {
                if (senateArr[i] == 'R') {
                    radiantActiveQueue.add(i);
                } else {
                    direActiveQueue.add(i);
                }
            }

            while (
                !(
                    radiantActiveQueue.isEmpty() &&
                    radiantWaitingQueue.isEmpty()
                ) ||
                !(direActiveQueue.isEmpty() && direWaitingQueue.isEmpty())
            ) {
                if (radiantActiveQueue.isEmpty() && direActiveQueue.isEmpty()) {
                    radiantActiveQueue = radiantWaitingQueue;
                    radiantWaitingQueue = new ArrayDeque<>();
                    direActiveQueue = direWaitingQueue;
                    direWaitingQueue = new ArrayDeque<>();
                }

                if (
                    radiantActiveQueue.isEmpty() && !direActiveQueue.isEmpty()
                ) {
                    if (radiantWaitingQueue.isEmpty()) {
                        return "Dire";
                    }
                    radiantWaitingQueue.poll();
                    direWaitingQueue.offer(direActiveQueue.poll());
                    continue;
                }

                if (
                    direActiveQueue.isEmpty() && !radiantActiveQueue.isEmpty()
                ) {
                    if (direWaitingQueue.isEmpty()) {
                        return "Radiant";
                    }
                    direWaitingQueue.poll();
                    radiantWaitingQueue.offer(radiantActiveQueue.poll());
                    continue;
                }

                int radiant = radiantActiveQueue.poll();
                int dire = direActiveQueue.poll();

                if (radiant < dire) {
                    radiantWaitingQueue.offer(radiant);
                } else {
                    direWaitingQueue.offer(dire);
                }
            }

            return "Radiant";
        }
    }
}
