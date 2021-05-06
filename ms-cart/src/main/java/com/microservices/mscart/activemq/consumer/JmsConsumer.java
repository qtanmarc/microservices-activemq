package com.microservices.mscart.activemq.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.mscart.entities.Product;
import com.microservices.mscart.repositories.IProductRepository;

/**
 * @author taq
 */
@Component
public class JmsConsumer {

    @Autowired
    private IProductRepository productRepository;

    @JmsListener(destination = "${product.jms.destination}")
    public void consumeMessage(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(data, Product.class);
            productRepository.save(product);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
}
