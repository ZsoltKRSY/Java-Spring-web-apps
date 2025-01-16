package ro.utcluj.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.demo.model.Category;
import ro.utcluj.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"category"})
    List<Product> findAll();
}
