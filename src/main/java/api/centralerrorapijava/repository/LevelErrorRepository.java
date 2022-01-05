package api.centralerrorapijava.repository;

import api.centralerrorapijava.model.LevelError;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LevelErrorRepository extends CrudRepository<LevelError, Long>{
    List<LevelError> findAll();
}
