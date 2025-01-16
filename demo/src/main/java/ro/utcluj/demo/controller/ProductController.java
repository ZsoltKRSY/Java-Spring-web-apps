package ro.utcluj.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.utcluj.demo.dto.ProductDto;
import ro.utcluj.demo.dto.UserDto;
import ro.utcluj.demo.model.Product;
import ro.utcluj.demo.model.RegistrationRequest;
import ro.utcluj.demo.service.CategoryService;
import ro.utcluj.demo.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping("/products")
    public String getProducts(Model model){
        List<ProductDto> productDtos = productService.getAllProducts();
        model.addAttribute("title", "Products");
        model.addAttribute("products", productDtos);
        return "products";
    }

    @GetMapping("/products/add-product")
    public String addProduct(@RequestParam(value="productAddSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Add new Product");
        model.addAttribute("productAddSuccess", success);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new Product());

        return "/products/add-product";
    }

    @PostMapping("/products/createProduct")
    public String createProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){
        System.out.println(product);
        ProductDto productDto = productService.addProduct(product);

        System.out.println(productDto);
        if(productDto != null) {
            redirectAttributes.addAttribute("productAddSuccess", "Success");
        }
        else{
            redirectAttributes.addAttribute("productAddSuccess", "Failed");
        }

        return "redirect:/products/add-product";
    }

    @PostMapping("/products/delete-product/{id}")
    public String deleteUserByUsername(@PathVariable (required = true) Integer id){
        productService.deleteProductById(id);

        return "redirect:/products";
    }
}
