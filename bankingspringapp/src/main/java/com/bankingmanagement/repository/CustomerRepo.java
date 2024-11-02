package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer,String> {
}
