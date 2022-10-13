package leetcode;

import leetcode.ListNode21.*;

public class DeleteNodeInALinkedList237 {

    class Solution {

        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
