package com.microservices.mscart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservices.mscart.entities.Product;

/**
 * @author taq
 */
public interface IProductRepository extends JpaRepository<Product, Long> {
}
