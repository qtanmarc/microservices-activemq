package com.microservices.mscart.controllers;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.microservices.mscart.entities.Product;

/**
 * @author taq
 */
@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public Product sendToCart(long id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        URI uri = URI.create("http://localhost:8045/product/sendToCart/" + id);
        return circuitBreaker.run(() -> this.restTemplate.getForObject(uri, Product.class),
            throwable -> defaultProduct());
    }

    public Product defaultProduct() {
        return new Product();
    }
}
