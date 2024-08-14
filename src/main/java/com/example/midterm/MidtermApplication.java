package com.example.midterm;

import com.example.midterm.product.Category;
import com.example.midterm.product.Product;
import com.example.midterm.product.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class MidtermApplication {

	private static final Logger log = LoggerFactory.getLogger(MidtermApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MidtermApplication.class, args);
	}

	@Bean
	CommandLineRunner openBrowser() {
		return args -> {
			try {
				String url = "http://localhost:8085/index.html";
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(url));
				} else {
					log.info("Open the following URL in your browser: {}", url);
				}
			} catch (Exception e) {
				log.error("Failed to open browser", e);
			}
		};
	}

//	@Bean
//	CommandLineRunner runner(ProductRepo productRepo) {
//		return args -> {
//			Product product = new Product(1, "Product 1", 10.0, 5.0, "image1.jpg", "Description 1", Category.ELECTRONICS);
//			productRepo.addProduct(product);
//		};
//	}
}
