package api.centralerrorapijava.user.repository;

import api.centralerrorapijava.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByEmail(String email);
}
