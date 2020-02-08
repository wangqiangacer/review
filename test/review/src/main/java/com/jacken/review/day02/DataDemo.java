package com.jacken.review.day02;

public class DataDemo {
    public static void main(String[] args) {
        int a=128;
        System.out.println(Integer.toBinaryString(a));
        int b=129;
        System.out.println(Integer.toBinaryString(b));
        //只有两个位上位1才是1  所以是128  与运算
        System.out.println(a&b);
        //或运算  两个位只要有一个为1，那么结果就是1，否则就为0，
        System.out.println(a|b);
        System.out.println("**********************");
        int c=2;
        System.out.println(Integer.toBinaryString(c));
        System.out.println(~c);
        System.out.println("---------------");
        int m =15;
        int n =2;
        System.out.println(Integer.toBinaryString(m)+"----"+Integer.toBinaryString(n));
        //
        System.out.println(m^n);

        int i = Integer.parseInt("1101", 2);
        System.out.println(i);


    }
}
