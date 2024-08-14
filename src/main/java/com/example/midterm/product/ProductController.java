package com.example.midterm.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("")
    ProductResponse getAllProducts() {
        List<Product>  products = productRepo.findAll();
        return new ProductResponse(products);
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) {
        Optional<Product> product = productRepo.getProductById(id);
        if(product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = productRepo.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/name/{name}")
    Product getProductByName(@PathVariable String name) {
        Optional<Product> product = productRepo.getProductByName(name);
        if(product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @GetMapping("/price/{price}")
    Product getProductByPrice(@PathVariable BigDecimal price) {
        Optional<Product> product = productRepo.getProductByPrice(price);
        if(product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addProduct(@RequestBody Product product) {
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
