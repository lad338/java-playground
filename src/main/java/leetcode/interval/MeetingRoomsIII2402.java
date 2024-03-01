package leetcode.interval;

import java.util.*;

public class MeetingRoomsIII2402 {

    class Solution {

        public int mostBooked(int n, int[][] meetings) {
            int[] roomCount = new int[n];
            PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
            PriorityQueue<int[]> usedRooms = new PriorityQueue<>((a, b) -> { // (ending, roomIndex)
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });
            int result = 0;
            int maxCount = 0;

            for (int i = 0; i < n; i++) {
                availableRooms.offer(i);
            }

            Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

            for (int[] meeting : meetings) {
                int start = meeting[0];
                int end = meeting[1];

                //handle all finished meetings
                while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                    availableRooms.offer(usedRooms.poll()[1]);
                }

                //if no available rooms, find one that ends early and delay current meeting
                if (availableRooms.isEmpty()) {
                    int[] room = usedRooms.poll();
                    availableRooms.offer(room[1]);
                    end = (end - start) + room[0]; //duration + new starting time;
                }
                //handle current meeting
                int roomIndex = availableRooms.poll();
                usedRooms.offer(new int[] { end, roomIndex });

                //calculate if it has max meeting
                int newCount = roomCount[roomIndex] + 1;
                roomCount[roomIndex] = newCount;
                if (newCount > maxCount) {
                    result = roomIndex;
                    maxCount = newCount;
                }
                if (newCount == maxCount && roomIndex < result) {
                    result = roomIndex;
                }
            }

            return result;
        }
    }
}
