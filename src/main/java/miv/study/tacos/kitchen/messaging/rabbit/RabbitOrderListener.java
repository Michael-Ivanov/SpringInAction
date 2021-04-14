package miv.study.tacos.kitchen.messaging.rabbit;

import miv.study.tacos.Order;
import miv.study.tacos.kitchen.KitchenUI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderListener {

    private KitchenUI kitchenUI;

    @Autowired
    public RabbitOrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @RabbitListener(queues = "hello world queue")
    public void receiveOrder(Order order) {
        kitchenUI.displayOrder(order);
    }
}
