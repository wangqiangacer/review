package com.jacken.springbootrocketmq.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消息的消费者
 * 消费消息有两种模式
 * 广播模式和负载均衡模式
 *
 *
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        //创建消息的消费者
        DefaultMQPushConsumer  consumer=new DefaultMQPushConsumer("async-group-1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //订阅主题Topic和Tag
        consumer.subscribe("async-test-1","Tag-3");

        //设定消费模式 负载均衡 和广播模式
            //    BROADCASTING("broadcasting"),
        //    CLUSTERING("clustering");
        //默认使用的负载均衡的策略消费消息MessageModel.CLUSTERING
        consumer.setMessageModel(MessageModel.CLUSTERING);
        System.out.println("消费者启动完成！！！");
        //设置回调函数处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
               // System.out.println("接收到的消息--"+list);
                for (MessageExt messageExt : list) {
                    System.out.println("接收到的消息--->"+new String(messageExt.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }
}
