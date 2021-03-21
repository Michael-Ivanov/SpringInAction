package miv.study.tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import miv.study.tacos.Order;
import miv.study.tacos.Taco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private Logger logger = LoggerFactory.getLogger(JdbcOrderRepository.class);

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

        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();

        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoorder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);

    }


}
