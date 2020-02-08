package com.jacken.review.day03;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *先到的先等  等到全部集齐之后再执行另外一个线程
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("****召唤神龙****");
        });
        for (int i = 1; i <= 7; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"收集到第"+ finalI +"颗龙珠");
                try {
                    //收集到第一颗龙珠 必须等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
