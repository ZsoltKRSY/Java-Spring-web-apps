package ro.utcluj.demo.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.demo.dto.CategoryDto;
import ro.utcluj.demo.model.Category;

import java.util.List;

@Component
public class CategoryMapper {
    public CategoryDto categoryEntityToDto(Category category){
        return CategoryDto.builder()
                .category(category.getCategory())
                .build();
    }

    public List<CategoryDto> categoryListEntityToDto(List<Category> categories){
        return categories.stream()
                .map(this::categoryEntityToDto)
                .toList();
    }

    public Category categoryDtoToEntity(CategoryDto categoryDto){
        return Category.builder()
                .category(categoryDto.category())
                .build();
    }

    public List<Category> CategoryListDtoToEntity(List<CategoryDto> categoryDtos){
        return categoryDtos.stream()
                .map(this::categoryDtoToEntity)
                .toList();
    }
}
