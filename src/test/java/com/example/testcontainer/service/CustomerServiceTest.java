package com.example.testcontainer.service;

import com.example.testcontainer.containers.BasicContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerServiceTest extends BasicContainer {

    @Autowired
    CustomerService customerService;

    @Test
    void redisLockTest() {
        customerService.updateCustomer("sanzio","go");
    }

}