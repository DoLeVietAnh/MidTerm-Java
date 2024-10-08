package com.example.midterm.product;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepo {
    private List<Product> productList = new ArrayList<>();
    private final JdbcClient jdbcClient;
    private final JdbcTemplate jdbcTemplate;

    public ProductRepo(JdbcClient jdbcClient, JdbcTemplate jdbcTemplate) {
        this.jdbcClient = jdbcClient;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM Product")
                .query(Product.class)
                .list();
    }

    public List<String> getCategories() {
        String sql = "SELECT DISTINCT category FROM Product";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public Optional<Product> getProductById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Product WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    public Optional<Product> getProductByName(String name) {
        return jdbcClient.sql("SELECT * FROM Product WHERE name = :name")
                .param("name", name)
                .query(Product.class)
                .optional();
    }

    public Optional<Product> getProductByPrice(BigDecimal price) {
        return jdbcClient.sql("SELECT * FROM Product WHERE price = :price")
                .param("price", price)
                .query(Product.class)
                .optional();
    }

    public void addProduct(Product product) {
        var updated = jdbcClient.sql("INSERT INTO Product (id, name, price, discounted_price, image, description, category) VALUES (?,?,?,?,?,?,?)")
                .params(product.id(),product.name(), product.price(), product.discounted_price(), product.image(), product.description(), product.category().name())
                .update();

        Assert.state(updated == 1, "Failed to create product: " + product.name());
    }

    public void updateProduct(Integer id, Product product) {
        var updated = jdbcClient.sql("UPDATE Product SET name = ?, price = ?, discounted_price = ?, image = ?, description = ?, category = ? WHERE id = ?")
                .params(product.name(), product.price(), product.discounted_price(), product.image(), product.description(), product.category().name(), id)
                .update();

        Assert.state(updated == 1, "Failed to update product: " + product.name());
    }


    public void deleteProduct(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Product WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated==1,"Failed to delete product" + id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM Product")
                .query().listOfRows().size();
    }

    public void saveAll(List<Product> products) {
        products.stream().forEach(this::addProduct);
    }

    public List<Product> findByCategory(String category) {
        return jdbcClient.sql("SELECT * FROM Product WHERE category = :category")
                .param("category", category)
                .query(Product.class)
                .list();
    }

}
