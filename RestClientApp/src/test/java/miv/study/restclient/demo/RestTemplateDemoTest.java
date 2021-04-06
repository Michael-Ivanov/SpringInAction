package miv.study.restclient.demo;

import miv.study.tacos.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestTemplateDemoTest {

    @Autowired
    private RestTemplateDemo restTemplateDemo;

    @Test
    public void shouldPrintIngredient() {
        Ingredient ingredient = restTemplateDemo.getIngredientById("FLTO");
        System.out.println("Test ingredient: " + ingredient);
    }

}