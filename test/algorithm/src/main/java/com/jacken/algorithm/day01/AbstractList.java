package com.jacken.algorithm.day01;

public  abstract class AbstractList<E> implements  List<E> {
    private  int  size;

    public int size(){
        return  size;

    }
    public boolean isEmpty(){
        return  size==0;
    }

    public boolean contains(E element){
        return indexOf(element)!=-1;
    }

    @Override
    public void add(E element) {
        add(size,element);
    }
}
