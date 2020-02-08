package com.jacken.review.day02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁（等同于 排队按序类似于排队打饭 先来后到   排队 不按序）
 * 使用ReentrantLock 默认使用非公平锁
 * 非公平锁的有点在于比公平锁的吞吐量大  sync也是非公平锁
 * ************************************
 * 可重入锁（递归锁）：ReentrantLock和sync默认是非公平的可重入锁
 *
 *
 */
public class NonFairLock {
    public static void main(String[] args) {
        //This is equivalent to using {@code ReentrantLock(false)}.
        Lock   lock=new ReentrantLock();

    }
}
