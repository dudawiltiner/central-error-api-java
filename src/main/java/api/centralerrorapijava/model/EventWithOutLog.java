package api.centralerrorapijava.model;

import api.centralerrorapijava.model.LevelError;

public class EventWithOutLog {
    private Long id;

    private LevelError level;

    private String eventDescription;

    private String origin;

    private String eventDate;

    public EventWithOutLog(Long id, LevelError level, String eventDescription, String origin, String eventDate) {
        this.id = id;
        this.level = level;
        this.eventDescription = eventDescription;
        this.origin = origin;
        this.eventDate = eventDate;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LevelError getLevel() {
        return level;
    }


    public void setLevel(LevelError level) {
        this.level = level;
    }


    public String getEventDescription() {
        return eventDescription;
    }


    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getEventDate() {
        return eventDate;
    }


    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
