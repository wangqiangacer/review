package com.jacken.review.day02;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * ABA 问题解决方式
 * 1，原子引用 AtomicReference<>   AtomicReference<User>
 * 2.修改版本号 类似于时间戳
 */

@Data
@AllArgsConstructor
class  User{
    private  String userName;
    private  int  age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("zk",22);
        User user2 = new User("lisi",22);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(user1);
        //比较并交换 如果成功了更新成user2
       atomicReference.compareAndSet(user1,user2);
        System.out.println(atomicReference.get().toString());

    }
}
