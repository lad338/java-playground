package leetcode;

import leetcode.ListNode21.*;

public class MergeSortedList21 {

  static class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      if (list2 == null) {
        return list1;
      }
      if (list1 == null) {
        return list2;
      }
      if (list1.val <= list2.val) {
        return new ListNode(list1.val, mergeTwoLists(list1.next, list2));
      }
      return new ListNode(list2.val, mergeTwoLists(list1, list2.next));
    }
  }
}
