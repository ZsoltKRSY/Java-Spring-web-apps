package ro.utcluj.demo.dto;

import lombok.Builder;
import ro.utcluj.demo.model.Category;

@Builder
public record ProductDto(Long id, String name, Category category, Long stock, Long price) {
}
