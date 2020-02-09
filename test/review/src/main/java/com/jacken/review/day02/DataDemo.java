package com.jacken.review.day02;

public class DataDemo {
    public static void main(String[] args) {
        int a=128;
        System.out.println(Integer.toBinaryString(a));//10000000
        int b=129;
        System.out.println(Integer.toBinaryString(b));//10000001
        //只有两个位上位1才是1  所以是128  与运算
        System.out.println(a&b);//128
        //或运算  两个位只要有一个为1，那么结果就是1，否则就为0，
        System.out.println(a|b);//129
        System.out.println("**********************");
        int c=2;
        System.out.println(Integer.toBinaryString(c));//0010
        System.out.println(~c);//-3
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
