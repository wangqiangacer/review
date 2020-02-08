package com.jacken.review.day02;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读取一个资源类  读取共享资源应该可以同时进行
 *
 * 读写锁的理论知识
 * 1.读读可以共存
 * 2.读写不能共存
 * 3.写写不能共存
 */

class MyCache{
    //缓存的东西很快 保证可见性
    private  volatile  Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock readWriteLock  =new ReentrantReadWriteLock();

    public  void put(String key,Object  value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写入"+key);
            map.put(key,value);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    //读取时可以共享的
    public  void get(String key){

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在读取"+key);
            Object value = map.get(key);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"读取完成"+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new     Thread(()->{
                myCache.put(finalI+"",finalI);
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new     Thread(()->{
                myCache.get(finalI+"");
            },String.valueOf(i)).start();
        }
    }
}
