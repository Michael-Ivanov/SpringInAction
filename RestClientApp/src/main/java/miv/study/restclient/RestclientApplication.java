package miv.study.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestclientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("Creating restTemplate bean...");
        return new RestTemplate();
    }

}
