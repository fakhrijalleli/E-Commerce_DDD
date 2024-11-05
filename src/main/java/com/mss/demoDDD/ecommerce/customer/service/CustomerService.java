package com.mss.demoDDD.ecommerce.customer.service;


import com.mss.demoDDD.ecommerce.customer.model.Address;
import com.mss.demoDDD.ecommerce.customer.model.Customer;
import com.mss.demoDDD.ecommerce.customer.model.PaymentMethod;
import com.mss.demoDDD.ecommerce.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setEmail(updatedCustomer.getEmail());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


    public void addAddress(Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.addAddress(address);
        customerRepository.save(customer);
    }

    public void removeAddress(Long customerId, Long addressId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.getAddresses().removeIf(address -> address.getId().equals(addressId));
        customerRepository.save(customer);
    }

    public void addPaymentMethod(Long customerId, PaymentMethod paymentMethod) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.addPaymentMethod(paymentMethod);
        customerRepository.save(customer);
    }

    public void removePaymentMethod(Long customerId, Long paymentMethodId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.getPaymentMethods().removeIf(method -> method.getId().equals(paymentMethodId));
        customerRepository.save(customer);
    }

}
