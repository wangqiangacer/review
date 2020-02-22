package com.jacken.springbootrocketmq.base.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.concurrent.TimeUnit;

/**
 * 发送同步消息 broker  会回传一个消息结果
 *
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        //1.创建生产者 producer
        DefaultMQProducer  defaultMQProducer=new DefaultMQProducer("group-1");
        //指定nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动producer
        defaultMQProducer.start();

        //创建消息对象
        for (int i = 0; i < 10; i++) {
            Message message=new Message();
            message.setTopic("test-2");
            message.setTags("Tag");
            message.setBody("我爱你龚玲forever".getBytes());
            SendResult result = defaultMQProducer.send(message);
            SendStatus sendStatus = result.getSendStatus();
            String msgId = result.getMsgId();
            MessageQueue messageQueue = result.getMessageQueue();
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送结果："+result+" 消息状态："+sendStatus+" 消息id "+msgId+ " 接收消息队列id "+queueId);
            TimeUnit.SECONDS.sleep(1);
        }

        defaultMQProducer.shutdown();

    }
}
