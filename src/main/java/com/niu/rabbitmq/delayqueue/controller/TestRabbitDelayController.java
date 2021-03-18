package com.niu.rabbitmq.delayqueue.controller;

import com.niu.rabbitmq.delayqueue.config.DelaySender;
import com.niu.rabbitmq.delayqueue.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试延迟队列
 *
 * @author niuqingsong
 * @date 2021/3/18
 */
@RestController
@RequestMapping(value = "/delay")
public class TestRabbitDelayController {

    @Autowired
    private DelaySender delaySender;

    @GetMapping("/send")
    public String sendDelay() {
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("11111111");
        order1.setOrderName("小米手机");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("22222222");
        order2.setOrderName("华为手机");

        Order order3 = new Order();
        order3.setOrderStatus(2);
        order3.setOrderId("33333333");
        order3.setOrderName("苹果手机");

        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        delaySender.sendDelay(order3);
        return "test--ok";
    }
}