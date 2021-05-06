package com.microservices.msproduct.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.msproduct.entities.Product;
import com.microservices.msproduct.repositories.IProductRepository;

/**
 * @author taq
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${product.jms.destination}")
    private String jmsQueue;

    @PostMapping("/addOne")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PostMapping("/addList")
    public List<Product> addProductList(@RequestBody List<Product> products) {
        return productRepository.saveAll(products);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/info")
    public String getInfo() {
        return "product microservice";
    }

    @GetMapping("/sendToCart/{id}")
    public ResponseEntity<Product> sendToCart(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(product.get());
            jmsTemplate.convertAndSend(jmsQueue, jsonInString);
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
