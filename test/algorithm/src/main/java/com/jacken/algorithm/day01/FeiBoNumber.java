package com.jacken.algorithm.day01;

/**
 * 算法学习:复杂度
 *            0 1 2 3 4 5
 * 斐波那契树  0 1 1 2 3 5 8 13 ....
 * 时间复杂度：估算程序指令的执行次数
 * 空间复杂度: 估算程序需占用的存储空间
 */
public class FeiBoNumber {


    public static void main(String[] args) {

        System.out.println(fib2(100));
        System.out.println(fib1(100));
    }


    //时间复杂度  O(2^n)
    public  static  int fib1(int n){
        if(n<=1)
            return n;
        return  fib1(n-1)+fib1(n-2);
    }

    ////时间复杂度  O(n)
    public  static  int fib2(int n){
        if(n<=1)
            return n;
        int first=0;
        int second=1;
        for (int i = 0; i < n - 1; i++) {
            int sum=first+second;
            first=second;
            second=sum;
        }
        return second;

    }
}
