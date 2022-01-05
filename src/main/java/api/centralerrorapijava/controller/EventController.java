package api.centralerrorapijava.controller;

import api.centralerrorapijava.controller.advice.ResourceNotFoundException;
import api.centralerrorapijava.model.Event;
import api.centralerrorapijava.model.EventWithOutLog;
import api.centralerrorapijava.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired(required = true)
    private EventService eventService;

    @PostMapping
    @ApiOperation("Save one more error event")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 201, message = "A new error event saved")
    })
    public ResponseEntity<String> save(@Valid @RequestBody Event event) {
        this.eventService.save(event);
        return new ResponseEntity<>("sucess - A new error event saved", HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("List all the events")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 200, message = "All events founded")
    })
    public ResponseEntity<List<EventWithOutLog>> findAll(Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findAll(pageable));
        return responseToGet(list, "Events");
    }

    @GetMapping("/{id}")
    @ApiOperation("Show one event by id")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Id"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Id"),
            @ApiResponse(code = 200, message = "Event founded by Id")
    })
    public ResponseEntity<Event> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Event>(this.eventService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Events by Id")), HttpStatus.OK);
    }

    @GetMapping("/byLevel/{levelName}")
    @ApiOperation("Show events list by level")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Level"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Level"),
            @ApiResponse(code = 200, message = "Events founded by Level")
    })
    public ResponseEntity<List<EventWithOutLog>> findByLevel(@PathVariable("levelName") String levelName, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByLevelName(levelName, pageable));
        return responseToGet(list, "Events by Level");
    }

    @GetMapping("/byDescription/{eventDescription}")
    @ApiOperation("Show events list by description")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Description"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Description"),
            @ApiResponse(code = 200, message = "Events founded by Description")
    })
    public ResponseEntity<List<EventWithOutLog>> findByEventDescription(@PathVariable("eventDescription") String eventDescription, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByEventDescription(eventDescription, pageable));
        return responseToGet(list, "Events by Description");
    }

    @GetMapping("/byOrigin/{origin}")
    @ApiOperation("Show events list by origin")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Origin"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Origin"),
            @ApiResponse(code = 200, message = "Events founded by Origin")
    })
    public ResponseEntity<List<EventWithOutLog>> findByOrigin(@PathVariable("origin") String origin, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByOrigin(origin, pageable));
        return responseToGet(list, "Events by Origin");
    }

    @GetMapping("/byQuantity/{quantity}")
    @ApiOperation("Show events list by quantity")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Quantity"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Quantity"),
            @ApiResponse(code = 200, message = "Events founded by Quantity")
    })
    public ResponseEntity<List<EventWithOutLog>> findByQuantity(@PathVariable("quantity") Integer quantity, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByQuantity(quantity, pageable));
        return responseToGet(list, "Events by Quantity");
    }

    @GetMapping("/byDate/{eventDate}")
    @ApiOperation("Show events list by date")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Error Server"),
            @ApiResponse(code = 400, message = "Bad Request. Invalid Date"),
            @ApiResponse(code = 401, message = "Unauthorized. Generate a token"),
            @ApiResponse(code = 404, message = "Not Found. No events registered with this Date"),
            @ApiResponse(code = 200, message = "Events founded by Date")
    })
    public ResponseEntity<List<EventWithOutLog>> findByEventDate(@PathVariable("eventDate") String eventDate, Pageable pageable) {
        List<EventWithOutLog>  list = toListEventsWithOutLog(this.eventService.findByEventDate(eventDate, pageable));
        return responseToGet(list, "Events by Date");
    }

    public List<EventWithOutLog> toListEventsWithOutLog(List<Event> events) {
        List<EventWithOutLog> newList = new ArrayList<>();
        for (Event item : events) {
            newList.add(new EventWithOutLog(item.getId(), item.getLevel(), item.getEventDescription(), item.getOrigin(), item.getEventDate()));
        }

        return newList;
    }

    public ResponseEntity<List<EventWithOutLog>> responseToGet(List<EventWithOutLog> list, String name) {
        if(list.size() == 0) {
            throw new  ResourceNotFoundException(name);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
