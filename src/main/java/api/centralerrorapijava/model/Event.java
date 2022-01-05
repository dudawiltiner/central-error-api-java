package api.centralerrorapijava.model;

import api.centralerrorapijava.model.LevelError;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="level_id")
    private LevelError level;

    @Column(name = "event_description")
    @NotNull
    @NotBlank
    private String eventDescription;

    @Column(name = "event_log")
    @NotNull
    @NotBlank
    private String eventLog;

    @NotNull
    private String origin;

    @NotNull
    @Column(name = "event_date")
//    Não coloquei no formato de date pois com string fica melhor para as buscas
    private String eventDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLog() {
        return eventLog;
    }

    public void setEventLog(String eventLog) {
        this.eventLog = eventLog;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public LevelError getLevel() {
        return level;
    }

    public void setLevel(LevelError level) {
        this.level = level;
    }
}
