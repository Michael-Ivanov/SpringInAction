package miv.study.tacos.jpadatarepository;

import miv.study.tacos.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoJpaRepository extends PagingAndSortingRepository<Taco, Long> {
}
