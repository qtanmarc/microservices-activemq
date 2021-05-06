package com.microservices.mscart.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.mscart.entities.Product;
import com.microservices.mscart.repositories.IProductRepository;

/**
 * @author taq
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping("/getProducts")
    public List<Product> getCartProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/deleteOne/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/deleteAll")
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @GetMapping("/info")
    public String getInfo() {
        return "cart microservice";
    }
}
