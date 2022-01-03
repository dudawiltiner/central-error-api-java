package api.centralerrorapijava.levelerror.repository;

import api.centralerrorapijava.levelerror.model.LevelError;
import api.centralerrorapijava.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LevelErrorRepository extends CrudRepository<LevelError, Long>{
    List<LevelError> findAll();
}
