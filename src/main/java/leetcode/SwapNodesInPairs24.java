package leetcode;

public class SwapNodesInPairs24 {

    class Solution {

        public ListNode swapPairs(ListNode head) {
            if (head == null) {
                return null;
            }

            if (head.next == null) {
                return head;
            }

            ListNode root = head.next;

            ListNode previous = null;
            ListNode front = head.next;
            ListNode back = head;

            while (front != null) {
                System.out.println(back.val);
                System.out.println(front.val);

                back.next = front.next;
                front.next = back;
                if (previous != null) {
                    previous.next = front;
                }
                previous = back;

                back = back.next;
                if (back == null) {
                    break;
                }
                if (back.next != null) {
                    front = back.next;
                } else {
                    break;
                }
            }

            return root;
        }
    }
}
