package com.jacken.review.day03;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的 一个是用于多个共享资源的互斥使用  另外是用于并发线程数的控制
 *
 * case:三个停车位 6台车去抢
 *
 * 多个线程抢多份资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //三个停车位 非公平锁
        Semaphore  semaphore=new Semaphore(1,true);
        for (int i = 1; i <=100; i++) {
            new Thread(()->{
                try {
                    //获得停车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    Random random = new Random();
                    int nextInt = random.nextInt(5);
                    TimeUnit.SECONDS.sleep(nextInt);

                    System.err.println(Thread.currentThread().getName()+"停车"+nextInt+"秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放停车位
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
