package com.jacken.review.day02;

public class SingletonDemo {

    //禁止指令重排
    private  static volatile SingletonDemo instance=null;
    private  SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"我是构造函数SingletonDemo()");
    }

    //采用双端检索机制
    public  static SingletonDemo  getInstance(){
        if (instance ==null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
