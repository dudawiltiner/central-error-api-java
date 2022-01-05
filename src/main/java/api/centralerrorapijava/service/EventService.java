package api.centralerrorapijava.service;

import api.centralerrorapijava.model.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface EventService {
    Event save(Event event);
    List<Event> findAll(Pageable pageable);
    Optional<Event> findById(Long id);
    List<Event> findByLevelName(String levelName, Pageable pageable);
    List<Event> findByEventDescription(String eventDescription, Pageable pageable);
    List<Event> findByOrigin(String origin, Pageable pageable);
    List<Event> findByEventDate(String eventDate, Pageable pageable);
    List<Event> findByQuantity(Integer quantity, Pageable pageable);
}
