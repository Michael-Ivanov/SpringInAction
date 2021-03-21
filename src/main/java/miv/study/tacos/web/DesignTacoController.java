package miv.study.tacos.web;

import miv.study.tacos.Ingredient;
import miv.study.tacos.Ingredient.Type;
import miv.study.tacos.Order;
import miv.study.tacos.Taco;
import miv.study.tacos.jpadatarepository.IngredientJpaRepository;
import miv.study.tacos.jpadatarepository.TacoJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private static final Logger logger = LoggerFactory.getLogger(DesignTacoController.class);

    private final IngredientJpaRepository ingredientJpaRepository;
    private final TacoJpaRepository tacoJpaRepository;

    @Autowired
    public DesignTacoController(IngredientJpaRepository ingredientJpaRepository, TacoJpaRepository tacoJpaRepository) {
        this.ingredientJpaRepository = ingredientJpaRepository;
        this.tacoJpaRepository = tacoJpaRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        Iterable<Ingredient> ingredientIterable = ingredientJpaRepository.findAll();
        ingredientIterable.forEach(ingredients::add);

        logger.info("add ingredients to model: " + ingredients);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            logger.warn("Taco object not valid: " + taco);
            return "/design";
        }

        Taco savedTaco = tacoJpaRepository.save(taco);
        order.addTaco(savedTaco);
        logger.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
