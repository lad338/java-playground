package leetcode;

public class SplitLinkedListInParts725 {

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

        public ListNode[] splitListToParts(ListNode head, int k) {
            ListNode[] result = new ListNode[k];
            ListNode[] ends = new ListNode[k];

            ListNode dummy = new ListNode(-1, head);
            result[0] = head;

            for (int i = 0; i < k; i++) {
                ends[i] = dummy;
            }

            ListNode latest = dummy;

            while (latest.next != null) {
                for (int step = 0; step < k && latest.next != null; step++) {
                    for (
                        int index = 0;
                        index < k && latest.next != null;
                        index++
                    ) {
                        if (index >= step) {
                            ends[index] = ends[index].next;
                            latest = ends[index];
                        }
                    }
                }
            }

            for (int i = 0; i < k - 1; i++) {
                if (result[i] == null) {
                    result[i + 1] = null;
                    continue;
                }
                result[i + 1] = ends[i].next;
                ends[i].next = null;
            }

            return result;
        }
    }
}
