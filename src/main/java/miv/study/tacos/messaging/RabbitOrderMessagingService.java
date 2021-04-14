package miv.study.tacos.messaging;

import miv.study.tacos.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService{

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("hello world queue", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties = message.getMessageProperties();
                properties.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
    }
}
