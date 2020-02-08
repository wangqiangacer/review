package com.jacken.review.day03;


import java.util.concurrent.locks.ReentrantLock;

/**
 * {
 *   public com.jacken.review.day03.MybatisCache();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 6: 0
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=3, args_size=1
 *          0: new           #2                  // class java/lang/Object
 *          3: dup
 *          4: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          7: dup
 *          8: astore_1
 *          9: monitorenter
 *         10: aload_1
 *         11: monitorexit
 *         12: goto          20
 *         15: astore_2
 *         16: aload_1
 *         17: monitorexit
 *         18: aload_2
 *         19: athrow
 *         20: new           #3                  // class java/util/concurrent/locks/ReentrantLock
 *         23: dup
 *         24: invokespecial #4                  // Method java/util/concurrent/locks/ReentrantLock."<init>":()V
 *         27: pop
 *         28: return
 */
public class MybatisCache {
    public static void main(String[] args) {
        synchronized (new Object()){
        //monitorexit因为防止死锁
        }

        new ReentrantLock();
    }


}
