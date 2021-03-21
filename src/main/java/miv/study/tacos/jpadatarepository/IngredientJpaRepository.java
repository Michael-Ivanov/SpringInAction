package miv.study.tacos.jpadatarepository;

import miv.study.tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientJpaRepository extends CrudRepository<Ingredient, String> {


}
