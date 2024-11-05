package com.mss.demoDDD.ecommerce.catalog.repository;

import com.mss.demoDDD.ecommerce.catalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}