package ro.utcluj.coding_events.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.coding_events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
