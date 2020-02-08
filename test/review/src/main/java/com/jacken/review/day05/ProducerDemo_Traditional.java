package com.jacken.review.day05;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 一个初始值为0的变量 两个线程对其交替操作 一个加一一个减一 来五轮
 *
 * 线程操作资源类  判断干活通知
 *
 */
class ShairData{

    private  int number =0;
    private Lock lock=new ReentrantLock();
    private Condition  condition=lock.newCondition();
    public  void increment() throws Exception{

        lock.lock();

        try {
            //判断
            while (number != 0){
               //等待
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public  void decrement() throws Exception{

        lock.lock();

        try {
            //判断
            while (number == 0){
                //等待
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


public class ProducerDemo_Traditional {
    public static void main(String[] args) {
        ShairData  shairData=new ShairData();
//        new   Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                try {
//                    shairData.increment();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"AAA").start();
//
//        new   Thread(()->{
//            for (int i = 0; i < 1000; i++) {
//                try {
//                    shairData.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"BBB").start();

        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            new Thread(()->{
                if(finalI %2==0){
                    try {
                        shairData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        shairData.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
