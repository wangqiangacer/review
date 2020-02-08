package com.jacken.review.day03;

import java.util.HashMap;

/**
 * jdk1.7是数组+链表
 * jdk1.8是数组+链表+红黑树
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1","2");
        String s = map.put("1", "3");
        String s1 = map.put("1", "4");
        System.out.println(s1);
        System.out.println(s);
        System.out.println("**************");
        //通过key计算key的hashcode
        System.out.println(Integer.toBinaryString(8));//1000
        System.out.println(Integer.toBinaryString(7));//0111
        System.out.println(8&7);
    }
}
