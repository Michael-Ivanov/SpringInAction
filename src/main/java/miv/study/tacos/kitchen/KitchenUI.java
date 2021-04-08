package miv.study.tacos.kitchen;

import lombok.extern.slf4j.Slf4j;
import miv.study.tacos.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(Order order) {
        log.info("Received order: " + order);
    }
}
