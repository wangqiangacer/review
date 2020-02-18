package com.jacken.algorithm.day01;

/**
 * 动态数组
 */
public class ArrayList<E> {

    private  int size=0;
    public static final int DEFAULT_SIZE=10;
    /**
     * 数组
     */
    private  E[]  elements;

    public  ArrayList(int capaticy){
        capaticy=(capaticy<DEFAULT_SIZE)?DEFAULT_SIZE:capaticy;
        elements= (E[]) new Object[capaticy];
    }

    public  ArrayList(){
        this(DEFAULT_SIZE);
    }

    public  void clear(){
        size=0;

    }

    public  int size(){
        return  size;

    }
    public  boolean isEmpty(){
        return  size==0;
    }

    public  boolean contains(E element){
        return indexOf(element)!=-1;
    }

    public  synchronized void add(E element){
        ///直接向size的位置放元素
            add(size,element);

    }

    public  E get(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }
        return elements[index];
    }

    public  E  set(int index,E element){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }

       E old= elements[index];
        elements[index]=element;
        return  old;
    }

    public  E remove(int  index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }
        E old=elements[index];
        for (int i = index+1; i <=size-1 ; i++) {
            elements[i-1]=elements[i];

        }
        size--;
        return  old;
    }



    public  int  indexOf(E element){
        for (int i = 0; i < size; i++) {
            if (elements[i]==element)return i;
        }
        return -1;
    }

    public  void add(int index,E element){
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }
        //size ==5
        ensureCapacity(size+1);
        for (int i = size-1; i >=index; i--) {
            elements[i+1]=elements[i];

        }
        elements[index]=element;
        size++;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int  oldCapacity=elements.length;
        if (oldCapacity>=capacity)return;
        //右移1位  比如8    1000  0100  运算速度非常高  新容量为旧容量的1.5倍
       // int newCapacity=oldCapacity+(oldCapacity>>1);
        int newCapacity=oldCapacity<<1;

        E[]  newElements=(E[]) new Object[newCapacity];


        for (int i = 0; i < size; i++) {
            newElements[i]=elements[i];
        }
        //自己的引用也要指向新的数组
        elements=newElements;

        System.out.println("*********oldCapacity 扩容为********"+newCapacity);

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size="+size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i!=0){
                stringBuilder.append(",");
            }
            stringBuilder.append(elements[i]);
//            if (i!=size-1){
//                stringBuilder.append(",");
//            }
        }

                stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
