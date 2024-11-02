package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepo extends MongoRepository<Loan,String> {
}
