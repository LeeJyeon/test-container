package com.example.testcontainer.repository;

import com.example.testcontainer.containers.BasicContainer;
import com.example.testcontainer.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest extends BasicContainer {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void 테스트() {
        Customer customer = new Customer();
        customer.setName("sanzio");

        customerRepository.save(customer);

        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach(
                e-> System.out.println(e.toString())
        );
    }
}