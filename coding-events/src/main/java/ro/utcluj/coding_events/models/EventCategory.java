package ro.utcluj.coding_events.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @Size(min = 3, message = "Name must be at least 3 characters long!")
    private String name;

    @OneToMany(mappedBy = "category")
    private final List<Event> events = new ArrayList<>();

    public EventCategory(String name){
        this.name = name;
    }

    public EventCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString(){
        return name;
    }
}
