package leetcode;

public class ReorderList143 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        public void reorderList(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            ListNode slow = dummy;
            ListNode fast = dummy;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode secondHalf = slow.next;
            slow.next = null;
            ListNode reversedSecondHalf = reverseList(secondHalf);

            dummy = dummy.next;
            while (dummy != null) {
                ListNode next = dummy.next;
                dummy.next = reversedSecondHalf;
                if (reversedSecondHalf != null) {
                    reversedSecondHalf = reversedSecondHalf.next;
                    dummy.next.next = next;
                }
                dummy = next;
            }
        }

        private ListNode reverseList(ListNode node) {
            ListNode previous = null;
            while (node != null) {
                ListNode next = node.next;
                node.next = previous;
                previous = node;
                node = next;
            }
            return previous;
        }
    }
}
