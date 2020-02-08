package com.jacken.review.day03;


import java.util.concurrent.CountDownLatch;

/**
 *让一些线程阻塞直到另外一些线程完成一系列操作后才被唤醒
 * 当一个线程或者多个线程调用await方法时，调用线程会被阻塞，其他线程
 * 调用countDown方法会将技术器减一
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch  countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
//        Thread.yield();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"班长最后关门走人");

    }
}
