package miv.study.tacos.jpadatarepository;

import miv.study.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoJpaRepository extends CrudRepository<Taco, Long> {
}
