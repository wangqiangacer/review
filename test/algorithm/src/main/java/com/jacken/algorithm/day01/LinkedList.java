package com.jacken.algorithm.day01;

/**
 * 链表是一种链式存储的线性表  所有的元素内存地址 不一定是连续的
 * 每一个Node 内部存储 元素自己的值和下一个元素的地址值
 *
 */
public class LinkedList<E> extends AbstractList<E> {
    //链表的元素个数
    private  int  size;
    //链表总元素的第一个
    private Node<E>  first;

    @Override
    public void clear() {
        size=0;
        first=null;
    }

    /**
     * 链表的复杂get复杂度
     * 最好O(1):直接获取链表的第一个元素
     * 最坏O(n): 获取元素的最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }


    /**
     * 链表的复杂get复杂度
     * 最好O(1):直接获取链表的第一个元素
     * 最坏O(n): 获取元素的最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public E set(int index, E element) {
        //返回index原来的东西
        E element1 = node(index).element;
        element1=element;
        return element1;
    }

    /**
     * 链表的复杂get复杂度
     * 最好O(1):直接获取链表的第一个元素
     * 最坏O(n): 获取元素的最后一个元素
     * 平均O(n)
     * @param index
     * @return
     */
    @Override
    public void add(int index, E element) {
        if(index==0){
           first= new Node<>(element,first);
        }else {
            Node<E> prev= node(index-1);
            prev.next= new Node<E>(element,prev.next);
        }

      size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node=first;
        if(index==0){
            first=first.next;
        }else {
            Node<E> prev= node(index-1);
            node=prev.next;
            prev.next=node.next;
        }
        size--;

        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element==null){
            Node<E> node=first;
            for (int i = 0; i < size; i++) {
                if (node.element==null)return i;
                node=node.next;
            }
        }else {
            Node<E> node=first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element))return i;
                node=node.next;
            }
        }

        return -1;
    }

    private  Node<E> node(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }

        Node<E> node=first;
        for (int i = 0; i < index; i++) {
            node=node.next;
        }

        return  node;
    }


    //内部内 存储的泛型是任意对象
    private static   class  Node<E>{
        //
        E  element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finallize  destroy");
            super.finalize();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size=" + size).append(", [");
        Node<E> node = first;
//        for (int i = 0; i < size; i++) {
//            if (i != 0) {
//                stringBuilder.append(",");
//            }
//            stringBuilder.append(node.element);
//            node = node.next;
//            stringBuilder.append("]");
//        }

        while (node != null){
            node=node.next;
        }
        return stringBuilder.toString();
    }
}
