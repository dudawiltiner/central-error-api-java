package api.centralerrorapijava.event.controller;

import api.centralerrorapijava.event.model.Event;
import api.centralerrorapijava.event.model.EventWithOutLog;
import api.centralerrorapijava.event.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired(required = true)
    private EventService eventService;

    @PostMapping
    @ApiOperation("Save one more error event")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "A new error event saved")})
    public ResponseEntity<String> save(@Valid @RequestBody Event event) {
        this.eventService.save(event);
        return new ResponseEntity<>("A new error event saved", HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("List all the events")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "All events founded")})
    public ResponseEntity<List<EventWithOutLog>> findAll(Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findAll(pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Show one event by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Event founded")})
    public ResponseEntity<Event> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Event>(this.eventService.findById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/byLevel/{levelName}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<EventWithOutLog>> findById(@PathVariable("levelName") String levelName, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByLevelName(levelName, pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byDescription/{eventDescription}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<EventWithOutLog>> findByEventDescription(@PathVariable("eventDescription") String eventDescription, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByEventDescription(eventDescription, pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byOrigin/{origin}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<EventWithOutLog>> findByOrigin(@PathVariable("origin") String origin, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByOrigin(origin, pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byQuantity/{quantity}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<EventWithOutLog>> findByQuantity(@PathVariable("quantity") Integer quantity, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByQuantity(quantity, pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byDate/{eventDate}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<EventWithOutLog>> findByEventDate(@PathVariable("eventDate") String eventDate, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByEventDate(eventDate, pageable));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public List<EventWithOutLog> toListEventsWithOutLog(List<Event> events) {
        List<EventWithOutLog> newList = new ArrayList<>();
        for (Event item : events) {
            newList.add(new EventWithOutLog(item.getId(), item.getLevel(), item.getEventDescription(), item.getOrigin(), item.getEventDate()));
        }

        return newList;
    }
}
