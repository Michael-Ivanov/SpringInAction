package miv.study.tacos.data;

import miv.study.tacos.Ingredient;
import miv.study.tacos.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbcTemplate;
    private IngredientRepository ingredientRepository;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate, IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {

        taco.setCreatedAt(new Date());
        PreparedStatementCreator statement = new PreparedStatementCreatorFactory(
                "insert into taco (name, createdat) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(statement, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbcTemplate.update(
                "insert into taco_ingredients (taco, ingredient) VALUES (?, ?)",
                tacoId, ingredient.getId());
    }

}


