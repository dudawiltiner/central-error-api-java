package api.centralerrorapijava.event.controller;

import api.centralerrorapijava.event.model.Event;
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

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired(required = true)
    private EventService eventService;

    @PostMapping
    @ApiOperation("Save one more error event")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "A new error event saved")})
    public ResponseEntity<Event> save(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(this.eventService.save(event), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("List all the events")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "All events founded")})
    public ResponseEntity<List<Event>> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.eventService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Event founded")})
    public ResponseEntity<Event> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Event>(this.eventService.findById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/byLevelName/{levelName}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<Event>> findById(@PathVariable("levelName") String levelName, Pageable pageable) {
        return new ResponseEntity<>(this.eventService.findByLevelName(levelName, pageable), HttpStatus.OK);
    }

    @GetMapping("/byEventDescription/{eventDescription}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<Event>> findByEventDescription(@PathVariable("eventDescription") String eventDescription, Pageable pageable) {
        return new ResponseEntity<>(this.eventService.findByEventDescription(eventDescription, pageable), HttpStatus.OK);
    }

    @GetMapping("/byOrigin/{origin}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<Event>> findByOrigin(@PathVariable("origin") String origin, Pageable pageable) {
        return new ResponseEntity<>(this.eventService.findByEventDescription(origin, pageable), HttpStatus.OK);
    }

    @GetMapping("/byQuantity/{quantity}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Events founded")})
    public ResponseEntity<List<Event>> findByQuantity(@PathVariable("quantity") String quantity, Pageable pageable) {
        return new ResponseEntity<>(this.eventService.findByEventDescription(quantity, pageable), HttpStatus.OK);
    }
}
