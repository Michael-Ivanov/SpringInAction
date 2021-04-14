package miv.study.tacos.kitchen.messaging.rabbit;

import miv.study.tacos.Order;
import miv.study.tacos.kitchen.messaging.OrderReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver implements OrderReceiver {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order receiveOrder() {

        return (Order) rabbitTemplate.receiveAndConvert("hello world queue");
    }
}
