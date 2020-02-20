package com.jacken.algorithm.leetCode.链表;



/**
 * cycle
 *
 * 快慢指针 可以判断链表是否有环
    如果快指针的val和慢指针的val 是一样的 则说明有环


 作业

 https://leetcode-cn.com/problems/remove-linked-list-elements/


 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/


 https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/
 */
public class CycleLinkedList {
    public static void main(String[] args) {

    }


    public  boolean hasCycle(ListNode head){

        if (head == null||head.next==null) return false;
        ListNode  slow=head;
        ListNode fast=head.next;
        while (fast!=null && fast.next!= null){

            slow=slow.next;
            fast=fast.next.next;
            if (fast==slow){
                return true;
            }
        }
        return false;
    }


}
