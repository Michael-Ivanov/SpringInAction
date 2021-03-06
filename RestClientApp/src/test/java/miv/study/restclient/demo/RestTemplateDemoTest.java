package miv.study.restclient.demo;

import miv.study.tacos.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@SpringBootTest
class RestTemplateDemoTest {

    @Autowired
    private RestTemplateDemo restTemplateDemo;

    @Test
    public void shouldPrintIngredient() {
        Ingredient ingredient = restTemplateDemo.getIngredientById("FLTO");
        System.out.println("Test getIngredientById: " + ingredient);
    }

    @Test
    public void shouldPrintIngredientFromMap() {
        Ingredient ingredient = restTemplateDemo.getIngredientByMapById("LETC");
        System.out.println("Test getIngredientByMapById: " + ingredient);
    }

    @Test
    public void shouldGetIngredientFromEntityAndPrint() {
        ResponseEntity<Ingredient> responseEntity =
                restTemplateDemo.getIngredientResponseEntity("SLSA");

        Ingredient ingredient = responseEntity.getBody();
        System.out.println("Test ingredient form ResponseEntity: " + ingredient);
    }

    @Test
    public void shouldUpdateIngredient() {
        Ingredient ingredient = new Ingredient("CHED", "Cheddar super cheese", Ingredient.Type.CHEESE);
        restTemplateDemo.updateIngredient(ingredient);
    }

    @Test
    public void shouldCreateNewIngredient() {
        Ingredient ingredient = new Ingredient("MOZZ", "Mozzarella cheese", Ingredient.Type.CHEESE);
        restTemplateDemo.createIngredient(ingredient);
    }

    @Test
    public void shouldCreateNewIngredientAndReturnLocation() {
        Ingredient ingredient = new Ingredient("PES", "Pesto sauce", Ingredient.Type.SAUCE);
        URI uri = restTemplateDemo.createIngredientReturnLocation(ingredient);
        System.out.println("New ingredient created. Location: " + uri);
    }

    @Test
    public void shouldDeleteIngredientById() {
        String id = "MOZZ";
        restTemplateDemo.deleteIngredientById(id);
        System.out.println("Ingredient with id " + id + " deleted");
    }

}