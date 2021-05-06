package com.microservices.msproduct.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservices.msproduct.entities.Product;

/**
 * @author taq
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
