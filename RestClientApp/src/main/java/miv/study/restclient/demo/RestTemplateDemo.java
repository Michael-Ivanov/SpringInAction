package miv.study.restclient.demo;

import miv.study.tacos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class RestTemplateDemo {

    @Autowired
    private ApplicationContext applicationContext;

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8888/api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    @PostConstruct
    private void run() {
        Ingredient ingredient = getIngredientById("FLTO");
        System.out.println("post construct: " + ingredient);
    }

}
