package miv.study.tacos.kitchen.messaging;

import miv.study.tacos.Order;

public interface OrderReceiver {

    Order receiveOrder();
}
