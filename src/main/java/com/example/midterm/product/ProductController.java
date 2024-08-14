package com.example.midterm.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("")
    List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) {
        Optional<Product> product = productRepo.getProductById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addProduct(@Valid @RequestBody Product product) {
        productRepo.addProduct(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateProduct(@PathVariable int id,@RequestBody Product product) {
        productRepo.updateProduct(id, product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable int id) {
        productRepo.deleteProduct(id);
    }

}
