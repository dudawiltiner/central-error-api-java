package api.centralerrorapijava.event.service;

import api.centralerrorapijava.event.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.List;
import java.util.Optional;


public interface EventService {
    Event save(Event event);
    List<Event> findAll(Pageable pageable);
    Optional<Event> findById(Long id);
    List<Event> findByLevelName(String levelName, Pageable pageable);
    List<Event> findByEventDescription(String eventDescription, Pageable pageable);
    List<Event> findByOrigin(String origin, Pageable pageable);
    List<Event> findByEventDate(DateFormat eventDate, Pageable pageable);
    List<Event> findByQuantity(Integer quantity, Pageable pageable);
}
