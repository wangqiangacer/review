package com.jacken.algorithm.leetCode.链表;


import com.jacken.algorithm.day01.ArrayList;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 *
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedListDemo {
    public static void main(String[] args) {
//            ListNode listNode=new ListNode()
//            deleteNode();

    }


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int  x) {
            val = x;
        }
    }

    /**
     * 先通过当前的node 找到下一个节点  覆盖当前节点的值  在 当前节点的值的next.next
     * @param node
     */
    public static void deleteNode(ListNode node) {

        node.val=node.next.val;
        node.next=node.next.next;
    }


}
