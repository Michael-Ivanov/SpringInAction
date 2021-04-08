package miv.study.tacos.kitchen.messaging.jms;

import miv.study.tacos.Order;

public interface OrderReceiver {

    Order receiveOrder();
}
