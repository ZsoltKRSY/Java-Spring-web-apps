package ro.utcluj.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.utcluj.demo.dto.ProductDto;
import ro.utcluj.demo.model.Product;
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

    @GetMapping("/add-product")
    public String addProduct(@RequestParam(value="productAddSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Add new Product");
        model.addAttribute("productAddSuccess", success);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new Product());

        return "/add-product";
    }

    @PostMapping("/products/createProduct")
    public String createProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes){
        ProductDto productDto = productService.addProduct(product);

        if(productDto != null) {
            redirectAttributes.addAttribute("productAddSuccess", "Success");
        }
        else{
            redirectAttributes.addAttribute("productAddSuccess", "Failed");
        }

        return "redirect:/add-product";
    }

    @GetMapping("/update-product/{id}")
    public String modifyProduct(@RequestParam(value="productUpdateSuccess", required = false) String success, Model model, @PathVariable (required = true) Integer id){
        model.addAttribute("title", "Modify Product with ID=" + id);
        model.addAttribute("productUpdateSuccess", success);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("oldProduct", productService.getProductById(id));
        model.addAttribute("newProduct", new Product());

        return "/update-product";
    }

    @PostMapping("/products/updateProduct/{id}")
    public String updateProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes,  @PathVariable (required = true) Integer id){
        product.setId(id);
        ProductDto productDto = productService.updateProduct(product);

        if(productDto != null) {
            redirectAttributes.addAttribute("productUpdateSuccess", "Success");
        }
        else{
            redirectAttributes.addAttribute("productUpdateSuccess", "Failed");
        }

        return "redirect:/update-product/" + id;
    }

    @PostMapping("/delete-product/{id}")
    public String deleteUserByUsername(@PathVariable (required = true) Integer id){
        productService.deleteProductById(id);

        return "redirect:/products";
    }
}
