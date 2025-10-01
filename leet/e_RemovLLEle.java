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
class e_RemovLLEle {
    public ListNode removeElements(ListNode head, int val) {
        //slow pointer approach
        ListNode x = new ListNode(-1);
        ListNode temp = x;
        ListNode y = head;
        while(y!=null){
            if(y.val == val ){
                y = y.next;
            }else{
                x.next = y;
                x = x.next;
                y = y.next;  
            }
        }
       //important 
        x.next=null;
        return temp.next;
    }
}