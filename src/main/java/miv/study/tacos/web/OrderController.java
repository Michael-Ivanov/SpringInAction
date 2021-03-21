package miv.study.tacos.web;

import miv.study.tacos.Order;
import miv.study.tacos.jpadatarepository.OrderJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderJpaRepository orderJpaRepository;

    @Autowired
    public OrderController(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        logger.info("Processing order: " + order);
        orderJpaRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
