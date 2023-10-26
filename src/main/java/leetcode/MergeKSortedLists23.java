package leetcode;

import java.util.*;
import leetcode.ListNode21.*;

public class MergeKSortedLists23 {

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

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }

            Deque<ListNode> queue = new ArrayDeque<>();

            for (ListNode node : lists) {
                if (node != null) {
                    queue.offer(node);
                }
            }

            while (queue.size() > 1) {
                ListNode list1 = queue.poll();
                ListNode list2 = queue.poll();
                queue.offer(mergeTwoLists(list1, list2));
            }

            if (queue.size() == 0) {
                return null;
            }

            return queue.poll();
        }

        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode result = new ListNode();
            ListNode head = result;

            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    head.next = list1;
                    list1 = list1.next;
                    head = head.next;
                } else {
                    head.next = list2;
                    list2 = list2.next;
                    head = head.next;
                }
            }

            if (list1 != null) {
                head.next = list1;
            }

            if (list2 != null) {
                head.next = list2;
            }

            return result.next;
        }
    }

    class NeetcodeSolution {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }

            while (lists.length > 1) {
                ListNode[] merged = new ListNode[lists.length % 2 == 0
                    ? lists.length / 2
                    : lists.length / 2 + 1];

                for (int i = 0; i < lists.length; i = i + 2) {
                    ListNode list1 = lists[i];
                    ListNode list2 = (i + 1) < lists.length
                        ? lists[i + 1]
                        : null;
                    merged[i / 2] = mergeTwoLists(list1, list2);
                }
                lists = merged;
            }
            return lists[0];
        }

        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode result = new ListNode();
            ListNode head = result;

            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    head.next = list1;
                    list1 = list1.next;
                    head = head.next;
                } else {
                    head.next = list2;
                    list2 = list2.next;
                    head = head.next;
                }
            }

            if (list1 != null) {
                head.next = list1;
            }

            if (list2 != null) {
                head.next = list2;
            }

            return result.next;
        }
    }
}
