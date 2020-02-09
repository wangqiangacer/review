package com.jacken.review.day04;

import java.util.concurrent.*;

/**
 * 线程池的总结
 * ThreadPoolExecutor
 * 线程池的七大参数：
 * corePoolSize:线程池常驻核心线程数
 * maximumPoolSize：线程池能够容纳同时执行的最大线程数 此值必须大于1
 * workqueue 阻塞队列： 类似于银行的候客区
 * threadFactory:线程工厂 一般用默认就可以
 * handler  ：线程的拒绝策略（四种拒绝策略）
 * 1.AbortPolicy（默认） 直接抛出异常 阻止系统运行
 * 2.CallerRunPolicy  回退调用者  谁调用我 就回退找谁
 * 3.DiscardOldestPolicy 丢弃队列中等待最久的任务
 * 4.DiscardPolicy 直接丢弃任务
 * keepAliveTime:多余空闲线程的存活时间（从最大线程数减少为corePoolSize）
 * unit:keepAliveTime  时间单位
 *
 * ****************************************************
 * 线程池的底层工作原理？
 * 线程池不允许Executors (LinkedBlockingQueue 21亿直接把堆占满从而导致oom) 而是通过ThreadPoolExecutor
 *
 * @@@@@@@@@@@@@@@@@@@@version
 * 如何合理的配置线程池？
 * 1.cpu密集型  CPU核数 +1  配置最大线程池
 * 2.io密集型
 * 2.1  CPU核数*2
 * 2.2  cpu核数 /(1-0.9)
 *
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //JdkThreadPool();
        //手写一个线程池 七大参数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService  executorService2=new ThreadPoolExecutor(2,5,1L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        //模拟10个用户来办理业务  每个用户就是来自外部的线程
        try {
            for (int i = 1; i <= 10; i++) {
                executorService2.execute(()->{
                    printHelloworld();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService2.shutdown();
        }


    }










    private static void JdkThreadPool() {
        //查看cup  核数
        // System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService executorService1=  Executors.newCachedThreadPool();
        ExecutorService  executorService2= Executors.newFixedThreadPool(10);//固定线程数的池子
        ExecutorService  executorService3= Executors.newSingleThreadExecutor();//创建一个线程的池子

        long l = System.currentTimeMillis();
        //模拟10个用户来办理业务  每个用户就是来自外部的线程
        try {

            for (int i = 1; i <= 100000; i++) {

                executorService2.execute(()->{
                    printHelloworld();
                    });
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService2.shutdown();
        }
        long l1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时"+(l1-l));
    }

    public  static void printHelloworld(){

        System.out.println(Thread.currentThread().getName()+"\t"+"hello world");
    }
}
