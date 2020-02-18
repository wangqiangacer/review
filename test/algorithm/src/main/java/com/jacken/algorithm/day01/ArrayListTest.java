package com.jacken.algorithm.day01;

public class ArrayListTest {
    public static void main(String[] args) {
        /**
         * 对象数组和int数组的区别
         * 局部变量是放在 栈中的
         * 堆空间放的是对象的地址值
         */
        ArrayList<Integer> arrayList = new ArrayList();

        for (int i = 0; i < 50; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList.toString());



    }
}
