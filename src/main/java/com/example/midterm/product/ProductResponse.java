package com.example.midterm.product;

import java.util.List;

public class ProductResponse {
    private List<Product> content;

    public ProductResponse(List<Product> content) {
        this.content = content;
    }

    public List<Product> getContent() {
        return content;
    }

    public void setContent(List<Product> content) {
        this.content = content;
    }
}
