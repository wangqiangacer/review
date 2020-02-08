package com.jacken.review.day01;


/**
 * 多线程环境中线程交替执行 由于编译器优化重排存在，两个线程中使用 变量无法保证一致性，结果无法预测
 */
public class ReSortDemo {
    int  a =0;
    boolean flag=false;
   public  void method1(){
       a=1;
       flag=true;
   }

   public  void method2(){
       if (flag){
           a=a+5;
           System.out.println("*****get value is"+a);
       }
   }
}
