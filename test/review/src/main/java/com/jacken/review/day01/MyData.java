package com.jacken.review.day01;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三种方式保证数据的一致性
 * 1.使用  Lock
 * 2.sync
 * 3.使用原子引用 atomic
 */
public class MyData {
//    int  number=0;
//
//   private  Lock lock= new ReentrantLock();
//    public  void addToSixty(){
//        this.number=60;
//    }
//
//    public   void addPlus(){
//        lock.lock();
//        try {
//            number++;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
    volatile int number=0;
        public  void addToSixty(){
        this.number=60;
    }

    public void addPlus(){
            number++;
    }

    //使用原子包装整形类 初始值为0  每次加一
    AtomicInteger atomicInteger= new AtomicInteger();
        public  void addAtomic(){
            atomicInteger.getAndIncrement();
        }
}
