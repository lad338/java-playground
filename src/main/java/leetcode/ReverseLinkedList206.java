package leetcode;

import java.util.*;
import leetcode.ListNode21.*;

public class ReverseLinkedList206 {

    class Solution {

        List<ListNode> list = new ArrayList<>();

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            fill(head);

            ListNode result = list.get(list.size() - 1);
            ListNode current = result;
            for (int i = list.size() - 2; i >= 0; i--) {
                current.next = list.get(i);
                current = current.next;
            }
            current.next = null;

            return result;
        }

        private void fill(ListNode node) {
            if (node != null) {
                list.add(node);
                fill(node.next);
            }
        }
    }

    class InPlaceSolution {

        public ListNode reverseList(ListNode head) {
            ListNode result = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = result;
                result = head;
                head = next;
            }
            return result;
        }
    }

    class RecursiveSolution {

        public ListNode reverseList(ListNode head) {
            return recursive(head, null);
        }

        private ListNode recursive(ListNode node, ListNode previous) {
            if (node == null) {
                return previous;
            }
            ListNode next = node.next;
            node.next = previous;
            return recursive(next, node);
        }
    }
}
