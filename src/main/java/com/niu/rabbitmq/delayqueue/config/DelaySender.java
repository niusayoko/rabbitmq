package com.niu.rabbitmq.delayqueue.config;

import com.niu.rabbitmq.delayqueue.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mq生产者，生产消息
 *
 * @author niuqingsong
 * @date 2021/3/18
 */
@Slf4j
@Component
public class DelaySender {

    /**
     * AMQP 高级消息队列协议
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送订单消息到延迟队列
     *
     * @param order
     * @return void
     * @author niuqingsong
     * @date 2021/3/18
     */
    public void sendDelay(Order order) {
        log.info("【订单生成时间】{},【1分钟后检查订单是否已经支付】{}", new Date().toString(), order.toString());

        /*
         * 这里声明的amqpTemplate接口，这个接口包含了发送和接收消息的一般操作，
         * 换种说法，它不是某个实现所专有的，所以AMQP存在于名称里。
         * 这个接口的实现与AMQP协议的实现紧密关联。
         * this.amqpTemplate.convertAndSend
         * 第一个参数为延迟交换机的名称，
         * 第二个为延时消费routing-key，
         * 第三个参数为order操作对象，
         * 第四个参数为消息
         */
        amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了
            // params.put("x-message-ttl", 5 * 1000);
            // 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1 * 1000 * 60 + "");
            return message;
        });
    }
}
