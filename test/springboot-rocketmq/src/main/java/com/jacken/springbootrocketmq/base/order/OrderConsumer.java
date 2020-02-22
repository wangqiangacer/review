package com.jacken.springbootrocketmq.base.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 订单顺序消费者
 */
public class OrderConsumer {
    public static void main(String[] args)  throws  Exception{
        //创建消息的消费者
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("order-group-1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //订阅主题Topic和Tag
        consumer.subscribe("orderTopic","*");

        //一个队列就是一个线程去消费
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt messageExt : list) {
                    System.out.println("消费消息:"+new String(messageExt.getBody())+" 线程名称"+Thread.currentThread().getName());
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("消费者启动");
    }
}
