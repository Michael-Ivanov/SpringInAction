package miv.study.tacos.kitchen.messaging.jms;

import miv.study.tacos.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jmsTemplate;
    private MessageConverter converter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter converter) {
        this.jmsTemplate = jmsTemplate;
        this.converter = converter;
    }

    @Override
    public Order receiveOrder() {
        Message message = jmsTemplate.receive("tacocloud.order.queue");
        try {
            return (Order) converter.fromMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("something id wrong");
    }
}
