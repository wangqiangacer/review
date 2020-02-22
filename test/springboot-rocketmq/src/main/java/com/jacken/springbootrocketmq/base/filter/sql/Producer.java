package com.jacken.springbootrocketmq.base.filter.sql;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * filter  sql根据 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        //1.创建生产者 producer
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("filter-group-sql");
        //指定nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动producer
        defaultMQProducer.start();

        //创建消息对象
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            Message message=new Message();
            message.setTopic("filter-test-sql");
            message.setTags("filter-Tag");
            message.setBody("我爱你龚玲forever".getBytes());
            message.putUserProperty("i",String.valueOf(i));
            SendResult result = defaultMQProducer.send(message);
            System.out.println("消息发送结果---"+result);



        }

        defaultMQProducer.shutdown();
    }
}
