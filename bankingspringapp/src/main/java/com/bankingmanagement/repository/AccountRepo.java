package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepo extends MongoRepository<Account,String> {
}
