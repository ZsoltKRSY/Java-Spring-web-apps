package ro.utcluj.demo.service;

import ro.utcluj.demo.dto.ProductDto;
import ro.utcluj.demo.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(Product product);

    ProductDto getProductById(Integer id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Product product);

    void deleteProductById(Integer id);
}
