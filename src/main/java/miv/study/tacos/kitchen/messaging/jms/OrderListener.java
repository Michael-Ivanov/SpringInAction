package miv.study.tacos.kitchen.messaging.jms;

import miv.study.tacos.Order;
import miv.study.tacos.kitchen.KitchenUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private KitchenUI kitchenUI;

    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        kitchenUI.displayOrder(order);
    }
}
