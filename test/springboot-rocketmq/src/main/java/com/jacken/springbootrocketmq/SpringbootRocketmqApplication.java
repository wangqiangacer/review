package com.jacken.springbootrocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * rocketmq  的入门demo
 *消息队列是一种先进先出的原则
 * 优点：
 * 1.应用程序的解耦
 * 2.流量的晓峰  单机吞吐量比较大 10万级
 *
 * mq系统复杂度提高
 * 如何保证消息没有被重复消费? 怎么处理消息丢失情况? 那么保证消息传递的顺序性
 *
 * mq 架构图
 * producer
 * broker: 用来接收生产者发来的消息  主节点主要处理的是读操作 从节点主要处理的是写操作
 *      -----broker 集群模式：单master 多master  多master多Slave （异步）  多master多Slave （同步）
 * name server  是broker的管理者
 * consumer
 broker  中有多个队列
 *
 *
 * 发送同步消息：消息发给mq之后只有等mq消费了消息之后会回调给消费者
 */
@SpringBootApplication
public class SpringbootRocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRocketmqApplication.class, args);
    }

}
