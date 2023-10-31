package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DeleteTheMiddleNodeOfALinkedList2095 {

    class Solution {

        private Map<Integer, ListNode> map = new HashMap<>();

        public ListNode deleteMiddle(ListNode head) {
            fillMap(head, 0);
            int n = map.size();
            int deleteIndex = n / 2;
            int previousIndex = deleteIndex - 1;
            if (previousIndex >= 0) {
                ListNode previous = map.get(previousIndex);
                previous.next = map.get(deleteIndex + 1);
                return head;
            } else {
                return null;
            }
        }

        private void fillMap(ListNode node, int index) {
            map.put(index, node);
            if (node.next != null) {
                fillMap(node.next, index + 1);
            }
        }
    }

    class OOneMemorySolution {

        public ListNode deleteMiddle(ListNode head) {
            if (head.next == null) {
                return null;
            }

            ListNode oneX = head;
            ListNode twoX = head;
            ListNode previous = head;

            while (twoX.next != null && twoX.next.next != null) {
                previous = oneX;
                oneX = oneX.next;
                twoX = twoX.next.next;
            }

            if (twoX.next == null) {
                previous.next = oneX.next;
            } else {
                oneX.next = oneX.next.next;
            }

            return head;
        }
    }
}
