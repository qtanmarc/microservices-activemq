package com.microservices.mscart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.microservices.mscart.entities.Product;

/**
 * @author taq
 */
@RestController
@RequestMapping("/circuitBreaker")
public class CircuitBreakerController {

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/sendToCart/{id}")
    public Product sendToCart(@PathVariable long id) {

        return productService.sendToCart(id);
    }
}
