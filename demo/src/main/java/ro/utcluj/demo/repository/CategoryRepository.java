package ro.utcluj.demo.repository;

import ro.utcluj.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategory(String category);
}
