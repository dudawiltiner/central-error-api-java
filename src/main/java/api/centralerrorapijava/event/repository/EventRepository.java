package api.centralerrorapijava.event.repository;

import api.centralerrorapijava.event.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long> {

    Page<Event> findAll(Pageable pageable);

    Optional<Event> findById(Long id);

    @Query(value = "select * from EVENTS event " +
            "INNER JOIN LEVELS level " +
            "ON level.level_id = event.level_id " +
            "WHERE level.levelName like %:levelName% ", nativeQuery = true)
    Page<Event> findByLevelName( String levelName, Pageable pageable);

//    @Query(value = "select event.id, level.level_name, " +
//            "event.event_description, event.origin, " +
//            "level.quantity, event.event_date " +
//            "from EVENT event " +
//            "INNER JOIN LEVEL level " +
//            "ON level.id = event.level_id " +
//            "WHERE event.event_description like %:eventDescription% ", nativeQuery = true)
    Page<Event> findByEventDescription( String eventDescription, Pageable pageable);

//    @Query(value = "select event.id, level.level_name, " +
//            "event.event_description, event.origin, " +
//            "level.quantity, event.event_date " +
//            "from EVENT event " +
//            "ON level.id = event.level_id " +
//            "WHERE event.origin like %:origin% ", nativeQuery = true)
    Page<Event> findByOrigin( String origin, Pageable pageable);

//    @Query(value = "select event.id, level.level_name, " +
//            "event.event_description, event.origin, " +
//            "level.quantity, event.event_date " +
//            "from EVENT event " +
//            "ON level.id = event.level_id " +
//            "WHERE event.event_date = :eventDate ", nativeQuery = true)
    Page<Event> findByEventDate(String eventDate, Pageable pageable);

    @Query(value = "select * from EVENTS event " +
            "INNER JOIN LEVELS level " +
            "ON level.level_id = event.level_id " +
            "WHERE level.quantity = :quantity ", nativeQuery = true)
    Page<Event> findByQuantity( Integer quantity, Pageable pageable);
}
