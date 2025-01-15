package ro.utcluj.demo.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.demo.dto.ProductDto;
import ro.utcluj.demo.model.Product;

import java.util.List;

@Component
public class ProductMapper {
    public ProductDto productEntityToDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .stock(product.getStock())
                .price(product.getPrice())
                .build();
    }

    public List<ProductDto> productListEntityToDto(List<Product> products){
        return products.stream()
                .map(this::productEntityToDto)
                .toList();
    }

    public Product productDtoToEntity(ProductDto productDto){
        return Product.builder()
                .id(productDto.id())
                .name(productDto.name())
                .category(productDto.category())
                .stock(productDto.stock())
                .price(productDto.price())
                .build();
    }

    public List<Product> roleListDtoToEntity(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::productDtoToEntity)
                .toList();
    }
}
