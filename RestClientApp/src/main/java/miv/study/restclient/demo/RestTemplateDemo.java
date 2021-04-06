package miv.study.restclient.demo;

import miv.study.tacos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateDemo {

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8888/api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }
}
