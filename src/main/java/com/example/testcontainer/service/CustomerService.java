package com.example.testcontainer.service;

import com.example.testcontainer.config.DistributedLock;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @DistributedLock(key = "#lockName.concat(' attempt to lock for ').concat(#action)")
    public void updateCustomer(String lockName, String action) {
    }
}
