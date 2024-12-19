package ro.utcluj.coding_events.models.dto;

import jakarta.validation.constraints.NotNull;
import ro.utcluj.coding_events.models.Event;
import ro.utcluj.coding_events.models.Tag;

public class TagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public TagDTO() { };

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
