package miv.study.tacos.jpadatarepository;

import miv.study.tacos.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
