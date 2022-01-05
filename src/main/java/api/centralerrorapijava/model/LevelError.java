package api.centralerrorapijava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity(name = "levels")
public class LevelError {
    @Id
    @Column(name = "level_id")
    private Long id;

    @Column(name = "level_name")
    @NotNull
    private String levelName;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @OneToMany(mappedBy="level")
    private List<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
