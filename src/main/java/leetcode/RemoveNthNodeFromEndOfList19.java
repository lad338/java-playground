package leetcode;

public class RemoveNthNodeFromEndOfList19 {

    class Solution {

        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode pointer1 = head;
            ListNode pointer2 = head;
            ListNode pointer3 = null;
            int i = 0;
            boolean isWait = true;
            while (pointer1.next != null) {
                pointer1 = pointer1.next;
                if (isWait) {
                    i++;
                    if (i == n) {
                        pointer2 = head.next;
                        pointer3 = head;
                        isWait = false;
                    }
                } else {
                    pointer2 = pointer2.next;
                    pointer3 = pointer3.next;
                }
            }

            if (pointer2 != null) {
                if (pointer2 == head) {
                    return head.next;
                }
                pointer3.next = pointer2.next;
            }
            return head;
        }
    }
}
