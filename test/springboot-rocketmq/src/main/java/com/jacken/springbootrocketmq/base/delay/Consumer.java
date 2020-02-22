package com.jacken.springbootrocketmq.base.delay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class Consumer {
    public static void main(String[] args)  throws Exception{
        //创建消息的消费者
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("delay-group-1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //订阅主题Topic和Tag
        consumer.subscribe("delay-test-1","Tag-6");
        System.out.println("消费者启动完成！！！");
        //设置回调函数处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for (MessageExt messageExt : list) {
                    System.out.println("接收到的消息ID--->"+messageExt.getMsgId()+"延时时间:"+(System.currentTimeMillis()-messageExt.getStoreTimestamp()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }
}
