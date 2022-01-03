package api.centralerrorapijava.event.repository;

import api.centralerrorapijava.event.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.text.DateFormat;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "INNER JOIN LEVEL level " +
            "ON level.id = event.level_id ", nativeQuery = true)
    Page<Event> findAll(Pageable pageable);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "INNER JOIN LEVEL level " +
            "ON level.id = event.level_id " +
            "WHERE event.id = :id ", nativeQuery = true)
    Optional<Event> findById(@Param("id") Long id);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "INNER JOIN LEVEL level " +
            "ON level.id = event.level_id +" +
            "WHERE level.levelName like %:levelName% ", nativeQuery = true)
    Page<Event> findByLevelName(@Param("levelName") String levelName, Pageable pageable);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "INNER JOIN LEVEL level " +
            "ON level.id = event.level_id " +
            "WHERE event.event_description like %:eventDescription% ", nativeQuery = true)
    Page<Event> findByEventDescription(@Param("eventDescription") String eventDescription, Pageable pageable);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "ON level.id = event.level_id " +
            "WHERE event.origin like %:origin% ", nativeQuery = true)
    Page<Event> findByOrigin(@Param("origin") String origin, Pageable pageable);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "ON level.id = event.level_id " +
            "WHERE event.event_date = :eventDate ", nativeQuery = true)
    Page<Event> findByEventDate(@Param("eventDate") DateFormat eventDate, Pageable pageable);

    @Query(value = "select event.id, level.level_name, " +
            "event.event_description, event.origin, " +
            "level.quantity, event.event_date " +
            "from EVENT event " +
            "ON level.id = event.level_id " +
            "WHERE level.quantity = :quantity ", nativeQuery = true)
    Page<Event> findByQuantity(@Param("quantity") Integer quantity, Pageable pageable);
}
