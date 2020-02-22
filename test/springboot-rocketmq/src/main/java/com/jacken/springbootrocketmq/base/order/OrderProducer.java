package com.jacken.springbootrocketmq.base.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 顺序消息的发送者 生产者
 */
public class OrderProducer {
    public static void main(String[] args) throws Exception {


        //1.创建生产者 producer
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("order-group-1");
        //指定nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动producer
        defaultMQProducer.start();

        List<OrderStep> stepList = OrderStep.build();
        System.out.println("producer  正在启动");
        for (int i = 0; i < stepList.size(); i++) {
            TimeUnit.SECONDS.sleep(2);
            String body = stepList.get(i) + "";
            //三个参数 1.消息对象 2.消息队列的选择器  3. 选择队列的业务标识
            Message  message=new Message("orderTopic","orderTag",body.getBytes());
            SendResult result = defaultMQProducer.send(message, new MessageQueueSelector() {
                /**
                 *
                 * @param list  消息队列的集合
                 * @param message  消息对象
                 * @param o  业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {

                    long orderId = (long) o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, stepList.get(i).getOrderId());


            System.out.println("发送结果"+result);
        }

        defaultMQProducer.shutdown();
    }
}
