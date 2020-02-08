package com.jacken.review.day02;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *  CAS 最关键的是UNSafe
 *  unsafe 大部分方法是调用的是native直接调用操作系统底层资源执行
 *  cas缺点:循环时间长 开销很大
 *
 *
 *
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger  atomicInteger=new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t get  value is "+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
