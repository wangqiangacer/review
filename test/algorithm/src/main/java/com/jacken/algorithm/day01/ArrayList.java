package com.jacken.algorithm.day01;

/**
 * 动态数组
 */
public class ArrayList<E> extends AbstractList<E> {

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
        for (int i = 0; i < size; i++) {
            elements[i]=null;
        }
        size=0;

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

    /**
     * 复杂度分析
     * 最好复杂度： 删除最后一个元素: O(1)
     * 最坏: 在最前面删除一个元素O(n)
     * 平均复杂度:O(n)
     * @param index
     * @return
     */
    public  E remove(int  index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",size:"+size);
        }
        E old=elements[index];
        for (int i = index+1; i <=size-1 ; i++) {
            elements[i-1]=elements[i];

        }
        size--;

        //动态数组的索容
        trim();
        return  old;
    }

    private void trim() {
        int capacity=elements.length;
        if (size>(capacity>>1)  || capacity<=DEFAULT_SIZE){
            //如果 剩余的数量大于一半就不缩容
            return;
        }

        int newCapacity=capacity>>1;
        E[]  newElements=(E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i]=elements[i];
        }
        //自己的引用也要指向新的数组
        elements=newElements;
        System.out.println(capacity+"缩容为"+newCapacity);

    }


    public  int  indexOf(E element){

        if (element==null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null)return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i]))return i;
            }
        }

        return -1;
    }

    /**
     * 复杂度分析
     *
     * 最好：在最后添加一个元素O(1)
     * 最坏: 在最前面添加一个元素O(n)
     * 平均复杂度:O(n)
     *
     * 均摊复杂度: O(1): 经过多次连续复杂度比较低得情况下，出现个别复杂度比较高的情况
     * @param index
     * @param element
     */
    public  void add(int index,E element){
       // if (element == null)return;
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
        int newCapacity=oldCapacity+(oldCapacity>>1);
        E[]  newElements=(E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i]=elements[i];
        }
        //自己的引用也要指向新的数组
        elements=newElements;

        System.out.println(oldCapacity +"扩容为"+newCapacity);

    }

    public static void main(String[] args) {
        int oldCapacity=10;
        int newCapacity=oldCapacity+oldCapacity>>1;
        System.out.println(newCapacity);
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
