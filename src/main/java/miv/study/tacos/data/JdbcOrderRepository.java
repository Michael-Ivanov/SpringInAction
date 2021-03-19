package miv.study.tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import miv.study.tacos.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_order_tacos");

        this.objectMapper = new ObjectMapper();

    }

    @Override
    public Order save(Order order) {

        return null;
    }
}
