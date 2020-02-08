package com.jacken.review.day05;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    //保证多线程变量的可见性
    private volatile   boolean Flag=true;//默认开启生产者消费者
    private AtomicInteger atomicInteger=new AtomicInteger();
    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws  Exception{
        String data=null;
        boolean retValue;
        while (Flag){
            data=atomicInteger.incrementAndGet()+"";
           retValue= blockingQueue.offer(data,2L, TimeUnit.SECONDS);
           if (retValue){
               System.out.println(Thread.currentThread().getName()+"\t插入数据"+data+"成功");
           }else {
               System.out.println(Thread.currentThread().getName()+"\t插入数据"+data+"失败");
           }
           TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"大老板叫停  flag=false，生产动作叫停");
    }

    public  void myConsumer() throws Exception{
        String result=null;
        while (Flag){
            result= blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == result || result.equals("")){
                //超过两秒没有取到蛋糕消费退出
                Flag=false;
                System.out.println(Thread.currentThread().getName()+"超过两秒没有取到蛋糕消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费成功"+result);
        }
    }


    public  void  stop() throws Exception{
        this.Flag=false;
    }
}
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();


        try {
            TimeUnit.SECONDS.sleep(5);
            myResource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
