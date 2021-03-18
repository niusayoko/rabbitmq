package com.niu.rabbitmq.delayqueue.config;

import com.niu.rabbitmq.delayqueue.pojo.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 接收者 （消费者）
 *
 * @author niuqingsong
 * @date 2021/3/18
 */
@Slf4j
@Component
public class DelayReceiver {

    /**
     * RabbitListener
     *
     * 所在的类首先是一个bean
     * BeanPostProcessor接口对每一个初始化完成的bean进行处理
     * 遍历bean中由用户自己定义的所有的方法，找出其中添加了@RabbitListener注解的方法
     * 读取上面找出的所有方法上@RabbitListener注解中的值，并为每一个方法创建一个RabbitListenerEndpoint，保存在RabbitListenerEndpointRegistrar类中
     * 在所有的bean都初始化完成，即所有@RabbitListener注解的方法都创建了endpoint之后，由我们配置的RabbitListenerContainerFactory将每个endpoint创建MessageListenerContainer
     * 最后创建上面的MessageListenerContainer
     * 此，全部完成，MessageListenerContainer启动后将能够接受到消息，再将消息交给它的MessageListener处理消息
     *
     * @param order
     * @param message
     * @param channel
     * @author niuqingsong
     * @date 2021/3/18
     */
    @RabbitListener(queues = {DelayRabbitConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(Order order, Message message, Channel channel) {
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", new Date(), order.toString());
        if (order.getOrderStatus() == 0) {
            order.setOrderStatus(2);
            log.info("【该订单未支付，取消订单】" + order.toString());
        } else if (order.getOrderStatus() == 1) {
            log.info("【该订单已完成支付】");
        } else if (order.getOrderStatus() == 2) {
            log.info("【该订单已取消】");
        }
    }
}
