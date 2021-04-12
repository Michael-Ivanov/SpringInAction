package miv.study.tacos.web.api;

import miv.study.tacos.Order;
import miv.study.tacos.jpadatarepository.OrderJpaRepository;
import miv.study.tacos.kitchen.messaging.OrderReceiver;
import miv.study.tacos.messaging.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderTacoRestController {

    private OrderJpaRepository orderRepository;
    private OrderMessagingService orderMessagingService;
    private OrderReceiver orderReceiver;

    @Autowired
    public OrderTacoRestController(OrderJpaRepository orderRepository,
                                   @Qualifier("rabbitOrderMessagingService") OrderMessagingService orderMessagingService,
                                   @Qualifier("rabbitOrderReceiver") OrderReceiver orderReceiver) {
        this.orderRepository = orderRepository;
        this.orderMessagingService = orderMessagingService;
        this.orderReceiver = orderReceiver;
    }

    @GetMapping("/send/{id}")
    public void sendOrder(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id).orElseThrow();

        orderMessagingService.sendOrder(order);
    }

    @GetMapping("/receive")
    public Order receiveOrder() {
        return orderReceiver.receiveOrder();
    }
}
