package com.jacken.review.day01;


import java.util.concurrent.TimeUnit;

/**
 * volatile （java虚拟机的轻量同步机制） 的三大特性:
 * 1.保证可见性（某一个线程修改变量之后，其他线程读取最新的变量）
 * 2.不保证原子性(数据的一致性 )
 * 3.禁止指令重排（底层编译器优化由于存在数据的依赖性）如果不使用volatile 底层编译器会自动重排
 * 主内存区域是内存共享 所有线程都可以访问，但是线程的读写操作必须在自己的工作内存中操作
 *
 */

//可见性的证明
//class  MyData{
//    volatile int  number=0;
//    public  void addToSixty(){
//        this.number=60;
//    }
//
//    public  void addPlus(){
//        number++;
//    }
//}
public class VolatileDemo {
    public static void main(String[] args)  {
        //不保证原子性证明
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                   // myData.addPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"get value is "+myData.atomicInteger);

    }
}

//    public void seeOkByVolatile(){
//        MyData myData = new MyData();
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"come in ");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            myData.addToSixty();
//
//        },"A").start();
//
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"come in ");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(myData.number);
//
//        },"B").start();
//    }
