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
class e_MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode temp = new ListNode(-1);
        ListNode out = temp;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                out.next = list1;
                list1 = list1.next;
                out = out.next;
            } else {
                out.next = list2;
                list2 = list2.next;
                out = out.next;
            }
        }

        while (list1 != null) {
            out.next = list1;
            list1 = list1.next;
            out = out.next;
        }
        while (list2 != null) {
            out.next = list2;
            list2 = list2.next;
            out = out.next;
        }
        return temp.next;
    }
}