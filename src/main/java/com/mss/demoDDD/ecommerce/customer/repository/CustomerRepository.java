package com.mss.demoDDD.ecommerce.customer.repository;

import com.mss.demoDDD.ecommerce.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
}
