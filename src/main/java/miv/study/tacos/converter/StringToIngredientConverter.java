package miv.study.tacos.converter;

import miv.study.tacos.Ingredient;
import miv.study.tacos.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
