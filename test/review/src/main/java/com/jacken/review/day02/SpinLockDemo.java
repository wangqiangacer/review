package com.jacken.review.day02;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁的好处:
 * 循环比较直到成功没有wait的阻塞
 */


public class SpinLockDemo {

    //原子引用线程  初始值为null

    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public  void  myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in ");
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+"正在自旋中");
        }
    }

    public  void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoke unLock()");
    }
    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();
        long l = System.currentTimeMillis();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        },"A").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.unLock();
        },"B").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l1 = System.currentTimeMillis();
        System.out.println("共耗时--------"+(l1-l));
    }
}
