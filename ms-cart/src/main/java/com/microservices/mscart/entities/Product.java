package com.microservices.mscart.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author taq
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id;

    private String name;

    private Double price;
}
