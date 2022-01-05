package api.centralerrorapijava.repository;

import api.centralerrorapijava.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long> {
    Page<Event> findAll(Pageable pageable);

    Optional<Event> findById(Long id);

    @Query(value = "select * from EVENTS event " +
            "INNER JOIN LEVELS level " +
            "ON level.level_id = event.level_id " +
            "WHERE level.level_name like %:levelName% ", nativeQuery = true)
    Page<Event> findByLevelName( String levelName, Pageable pageable);

    Page<Event> findByEventDescription( String eventDescription, Pageable pageable);

    Page<Event> findByOrigin( String origin, Pageable pageable);

    Page<Event> findByEventDate(String eventDate, Pageable pageable);

    @Query(value = "select * from EVENTS event " +
            "INNER JOIN LEVELS level " +
            "ON level.level_id = event.level_id " +
            "WHERE level.quantity = :quantity ", nativeQuery = true)
    Page<Event> findByQuantity( Integer quantity, Pageable pageable);
}
