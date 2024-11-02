package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepo extends MongoRepository<Branch,String> {
}
