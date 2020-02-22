package com.jacken.springbootrocketmq.base.filter.tag;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args)  throws Exception{
        //创建消息的消费者
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("filter-group-1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //订阅主题Topic和Tag
        consumer.subscribe("filter-test-1","filter-Tag || filter-Tag-1");
        System.out.println("消费者启动完成！！！");
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
