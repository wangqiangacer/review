package com.jacken.review.day05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列生产一个消费一个
 *你不消费我不生产
 */
public class SyncBlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new SynchronousQueue<>();
//
//        new Thread(()->{
//            try {
//                System.out.println(Thread.currentThread().getName()+"\t put 1");
//                blockingQueue.put("1");
//                System.out.println(Thread.currentThread().getName()+"\t put 2");
//                blockingQueue.put("2");
//                System.out.println(Thread.currentThread().getName()+"\t put 3");
//                blockingQueue.put("3");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"A").start();
//
//
//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(4);
//                System.out.println(Thread.currentThread().getName()+"\t sync take data is "+blockingQueue.take());
//                TimeUnit.SECONDS.sleep(4);
//                System.out.println(Thread.currentThread().getName()+"\t sync take data is "+blockingQueue.take());
//                TimeUnit.SECONDS.sleep(4);
//                System.out.println(Thread.currentThread().getName()+"\t sync take data is "+blockingQueue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"B").start();


        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    blockingQueue.put(i+"");
                    System.out.println(Thread.currentThread().getName()+"\t put"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();


        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(3);
                    String s = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()+"\t take data is "+s);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
