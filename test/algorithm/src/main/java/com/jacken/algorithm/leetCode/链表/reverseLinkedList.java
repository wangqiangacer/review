package com.jacken.algorithm.leetCode.链表;


import com.jacken.algorithm.day01.List;

/**
 * 反转一个单链表。
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class reverseLinkedList {


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int  x) {
            val = x;
        }
    }


    public  ListNode  reverseList(ListNode head){
        if (head==null)return null;
        //只有一个节点  反转之后还是本身
        if (head.next==null)return head;
        ListNode newHead=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }


    public  ListNode reverseList2(ListNode head){
        if (head==null)return null;
        //只有一个节点  反转之后还是本身
        if (head.next==null)return head;
        ListNode newHead=null;
        while (head!=null){
            ListNode tmp=head.next;
            head.next=newHead;
            newHead=head;
            head=tmp;

        }
        return newHead;
    }
}
