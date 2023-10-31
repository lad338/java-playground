package leetcode;

public class SortList148 {

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

        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }

            if (head.next == null) {
                return head;
            }

            ListNode previous = null;
            ListNode slower = head;
            ListNode faster = head;

            while (faster != null) {
                previous = slower;
                slower = slower.next;
                faster = faster.next;
                if (faster != null) {
                    faster = faster.next;
                } else {
                    break;
                }
            }
            ListNode right = sortList(previous.next);
            previous.next = null;
            ListNode left = sortList(head);
            return merge(left, right);
        }

        private ListNode merge(ListNode a, ListNode b) {
            ListNode root = new ListNode();
            ListNode current = root;

            while (a != null && b != null) {
                if (a.val <= b.val) {
                    current.next = a;
                    a = a.next;
                    current = current.next;
                } else {
                    current.next = b;
                    b = b.next;
                    current = current.next;
                }
            }

            if (a != null) {
                current.next = a;
            }

            if (b != null) {
                current.next = b;
            }

            return root.next;
        }
    }
}
