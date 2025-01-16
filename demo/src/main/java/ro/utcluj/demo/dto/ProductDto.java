package ro.utcluj.demo.dto;

import lombok.Builder;
import ro.utcluj.demo.model.Category;

@Builder
public record ProductDto(Integer id, String name, Category category, Long stock, Long price) {
}
