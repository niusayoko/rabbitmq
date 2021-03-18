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
        order1.setOrderId("123321123");
        order1.setOrderName("波音747飞机");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("2345123123");
        order2.setOrderName("豪华游艇");

        Order order3 = new Order();
        order3.setOrderStatus(2);
        order3.setOrderId("983676");
        order3.setOrderName("小米alpan阿尔法");

        delaySender.sendDelay(order1);
        delaySender.sendDelay(order2);
        delaySender.sendDelay(order3);
        return "test--ok";
    }
}