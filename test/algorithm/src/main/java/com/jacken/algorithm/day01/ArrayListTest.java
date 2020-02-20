package com.jacken.algorithm.day01;


/**
 * person  finalize  如果对象的引用为null的话  即对象即将被回收  则会调用 finalize
 *
 * 注意: equal :如果比较基本数据类型  则是比较的是数值
 *              如果是对象的话  则比较的对象的地址值
 * 动态数组明显缺点:  可能造成内存空间的大量浪费
 *
 * 复杂度的分析:
 * 最好情况复杂度： get()---O(1) set()--- O(1)
 * 最坏情况复杂度
 * 平均情况复杂度
 *
 */
public class ArrayListTest {
    public static void main(String[] args) {
        /**
         * 对象数组和int数组的区别
         * 局部变量是放在 栈中的
         * 堆空间放的是对象的地址值
         */
//        ArrayList<Integer> arrayList = new ArrayList();
//
//        for (int i = 0; i < 50; i++) {
//            arrayList.add(i);
//        }
//        System.out.println(arrayList.toString());
//        ArrayList<Person> arrayList=new ArrayList<>();
//        arrayList.add(new Person("111",12));
//        arrayList.clear();
//        System.gc();


        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }

        for (int i = 0; i < 50; i++) {
            list.remove(0);
        }

        System.out.println(list);


    }
}
