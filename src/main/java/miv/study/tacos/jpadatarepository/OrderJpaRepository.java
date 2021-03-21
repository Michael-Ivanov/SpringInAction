package miv.study.tacos.jpadatarepository;

import miv.study.tacos.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderJpaRepository extends CrudRepository<Order, Long> {
}
