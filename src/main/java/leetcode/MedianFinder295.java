package leetcode;

import java.util.PriorityQueue;

public class MedianFinder295 {

    class MedianFinder {

        PriorityQueue<Integer> firstHalf = new PriorityQueue<>((a, b) -> (b - a)
        );
        PriorityQueue<Integer> secondHalf = new PriorityQueue<>();

        public MedianFinder() {}

        public void addNum(int num) {
            if (firstHalf.isEmpty() && secondHalf.isEmpty()) {
                firstHalf.offer(num);
                return;
            }

            if (!firstHalf.isEmpty() && firstHalf.peek() >= num) {
                firstHalf.offer(num);
            } else if (!secondHalf.isEmpty() && secondHalf.peek() <= num) {
                secondHalf.offer(num);
            } else if (num > firstHalf.peek()) {
                secondHalf.offer(num);
            } else {
                firstHalf.offer(num);
            }

            while (firstHalf.size() - secondHalf.size() > 1) {
                secondHalf.offer(firstHalf.poll());
            }

            while (secondHalf.size() > firstHalf.size()) {
                firstHalf.offer(secondHalf.poll());
            }
        }

        public double findMedian() {
            if (firstHalf.size() > secondHalf.size()) {
                return firstHalf.peek();
            }

            return (firstHalf.peek() + secondHalf.peek()) / 2.0f;
        }
    }
    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
