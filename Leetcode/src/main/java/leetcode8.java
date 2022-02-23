/*将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
public class leetcode8 {
    /*
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

//初始化一个链表
        ListNodeInt dummyHead = new ListNodeInt(0);
        ListNodeInt cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                //这个链表的指针指向l1的这个数据
                cur.next = l1;
                //跟新这个新链表的指针
                cur = cur.next;
                //l1读取这个链表的下一个数据
                l1 = l1.next;
            } else {
                //这个链表的指针指向l2的这个数据
                cur.next = l2;
                //跟新这个新链表的指针
                cur = cur.next;
                //l2读取这个链表的下一个数据
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        //返回新建链表的下一个就是新的有序链表
        return dummyHead.next;
}*/
}
