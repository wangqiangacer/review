package com.jacken.springbootrocketmq.base.order;

import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
public class OrderStep {
    private  long  orderId;
    private  String desc;

    public OrderStep(long orderId, String desc) {
        this.orderId = orderId;
        this.desc = desc;
    }

    public OrderStep() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }

    public  static List<OrderStep> build(){
        ArrayList<OrderStep> list = new ArrayList<>();
        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(123l);
        orderStep.setDesc("创建");
        list.add(orderStep);
        OrderStep orderStep1 = new OrderStep();
        orderStep1.setOrderId(123l);
        orderStep1.setDesc("下单");
        list.add(orderStep1);

        OrderStep orderStep2 = new OrderStep();
        orderStep2.setOrderId(123l);
        orderStep2.setDesc("付款");
        list.add(orderStep2);


        OrderStep orderStep3 = new OrderStep();
        orderStep3.setOrderId(123l);
        orderStep3.setDesc("完成");
        list.add(orderStep3);



        OrderStep orderStep4 = new OrderStep();
        orderStep4.setOrderId(321l);
        orderStep4.setDesc("创建");
        list.add(orderStep4);

        OrderStep orderStep5 = new OrderStep();
        orderStep5.setOrderId(321l);
        orderStep5.setDesc("下单");
        list.add(orderStep5);



        return   list;
    }

    public static void main(String[] args) {
        List<OrderStep> list = build();
        for (OrderStep orderStep : list) {
            System.out.println(orderStep);
        }
    }
}
