package ro.utcluj.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ro.utcluj.demo.dto.ProductDto;
import ro.utcluj.demo.mapper.ProductMapper;
import ro.utcluj.demo.model.Product;
import ro.utcluj.demo.repository.CategoryRepository;
import ro.utcluj.demo.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto addProduct(Product product) {
        Product addedProduct = Product.builder()
                .name(product.getName())
                .category(product.getCategory())
                .stock(product.getStock())
                .price(product.getPrice())
                .build();
        return productMapper.productEntityToDto(productRepository.save(addedProduct));
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return productMapper.productEntityToDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.productListEntityToDto(productRepository.findAll());
    }

    @Override
    public ProductDto updateProduct(Product product) {
        return productMapper.productEntityToDto(productRepository.save(product));
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
