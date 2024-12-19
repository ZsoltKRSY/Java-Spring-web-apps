package ro.utcluj.coding_events.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.coding_events.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
}
