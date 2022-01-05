package api.centralerrorapijava.service;

import api.centralerrorapijava.model.Event;
import api.centralerrorapijava.repository.EventRepository;
import api.centralerrorapijava.model.LevelError;
import api.centralerrorapijava.repository.LevelErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LevelErrorRepository levelErrorRepository;

    @Override
    public Event save(Event event) {
        Long id = event.getLevel().getId();
        LevelError levelError = this.levelErrorRepository.findById(id).orElse(null);
        if(levelError != null) {
            levelError.setQuantity(levelError.getQuantity() + 1);
        }

        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAll(Pageable pageable) {
        return this.eventRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public List<Event> findByLevelName(String levelName, Pageable pageable) {
        return this.eventRepository.findByLevelName(levelName, pageable).getContent();
    }

    @Override
    public List<Event> findByEventDescription(String eventDescription, Pageable pageable) {
        return this.eventRepository.findByEventDescription(eventDescription, pageable).getContent();
    }

    @Override
    public List<Event> findByOrigin(String origin, Pageable pageable) {
        return this.eventRepository.findByOrigin(origin, pageable).getContent();
    }

    @Override
    public List<Event> findByEventDate(String eventDate, Pageable pageable) {
        return this.eventRepository.findByEventDate(eventDate, pageable).getContent();
    }

    @Override
    public List<Event> findByQuantity(Integer quantity, Pageable pageable) {
        return this.eventRepository.findByQuantity(quantity, pageable).getContent();
    }
}
