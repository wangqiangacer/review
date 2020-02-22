package com.jacken.springbootrocketmq.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 返回单向消息
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        //1.创建生产者 producer
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("oneway-group-1");
        //指定nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动producer
        defaultMQProducer.start();

        //创建消息对象
        for (int i = 0; i < 2; i++) {
            Message message=new Message();
            message.setTopic("oneway-test-1");
            message.setTags("Tag-4");
            message.setBody("我爱你龚玲forever".getBytes());
            defaultMQProducer.sendOneway(message);

            TimeUnit.SECONDS.sleep(1);
        }

        defaultMQProducer.shutdown();
    }
}
