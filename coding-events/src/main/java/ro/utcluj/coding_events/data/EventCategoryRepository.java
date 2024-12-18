package ro.utcluj.coding_events.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.coding_events.models.EventCategory;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
