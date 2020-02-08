package com.jacken.review.day02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    //初始化的值 和 初始化的版本号的
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
    new Thread(()->{
        atomicReference.compareAndSet(100,101);
        atomicReference.compareAndSet(101,100);
        System.out.println(Thread.currentThread().getName()+"完成ABA操作");
    },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+atomicReference.compareAndSet(100,2020));
        },"t2").start();


        new Thread(()->{
       // int stamp =atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t  第一次版本号 "+atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t  第二次版本号 "+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t  第三次版本号 "+atomicStampedReference.getStamp());
            },"t3").start();


        new Thread(()->{
            int stamp =atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t  第一次版本号 "+stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName()+"\t "+atomicStampedReference.getReference());
        },"t4").start();

    }

}
