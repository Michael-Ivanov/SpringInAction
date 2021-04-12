package miv.study.tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue");
    }

    @Bean
    public MessageConverter myMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
