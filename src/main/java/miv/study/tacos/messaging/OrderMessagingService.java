package miv.study.tacos.messaging;

import miv.study.tacos.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}
