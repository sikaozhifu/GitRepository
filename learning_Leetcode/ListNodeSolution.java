package com.test.leetcode;

public class ListNodeSolution {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     * @param node
     */
    public void deleteNode(ListNode node) {
        //https://www.cnblogs.com/xugenpeng/p/9847904.html解析很好
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。（递归）
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = null;
        if (l1.val < l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。（非递归）
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                temp.next = l2;
                l2 = l2.next;
            }else {
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }
        if (l1 != null){
            temp.next = l1;
        }
        if (l2 != null){
            temp.next = l2;
        }
        return head.next;
    }

    /**
     * 反转一个单链表。
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode pre = null;//当前结点的前一个结点
        ListNode next = null;//当前结点的下一个结点
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //快慢指针
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i ++){
            fast = fast.next;
        }
        if (fast == null){
            //此时要删除的为头结点
            head = head.next;
            return head;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
