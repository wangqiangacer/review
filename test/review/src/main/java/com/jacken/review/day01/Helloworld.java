package com.jacken.review.day01;

/**
 *  jdk   java 开发工具包  java development kit
 *
 *  jre  java 运行环境  java runtime  enviroment
 *
 *  每一个线程分配一个栈空间
 *  不同的方法对应一个独立的栈帧  栈的数据结构特性  FIFO  先进后出
 *
 *  栈帧 又包括 局部变量表  操作数栈  动态链接  方法出口
 */
public class Helloworld {
    //main 函数式程序的入口

    public  int computer(){
        //当方法返回之后  局部变量被释放
        int a =1;
        int b=2;
        int c=(a+b)*10;
        return  c;
    }
    public static void main(String[] args) {
        //没一个方法对应一个栈帧
        Helloworld helloworld = new Helloworld();
        int i = helloworld.computer();
        System.out.println(i);
    }

}
