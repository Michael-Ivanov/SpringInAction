package miv.study.tacos.jpadatarepository;

import miv.study.tacos.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, String> {


}
