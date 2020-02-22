package com.jacken.springbootrocketmq.base.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.concurrent.TimeUnit;

/**
 * 发送异步消息
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建生产者 producer
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("async-group-1");
        //指定nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动producer
        defaultMQProducer.start();

        //创建消息对象
        for (int i = 0; i < 6; i++) {
            Message message=new Message();
            message.setTopic("async-test-1");
            message.setTags("Tag-3");
            message.setBody("我爱你龚玲forever".getBytes());
            //发送异步消息
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    //发送成功的回调函数
                    System.out.println("发送成功,结果--->"+sendResult);
                    //这里可以做业务逻辑处理 根据返回的sendStatus的状态来判断继续执行下面的操作
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败"+ throwable);
                }
            });

            TimeUnit.SECONDS.sleep(2);
        }

        defaultMQProducer.shutdown();
    }
}
