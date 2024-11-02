package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BankRepo extends MongoRepository<Bank,String> {
    Optional<Bank> findByBankName(String name);

    List<Bank> findAllByBankName(String name);

    void deleteByBankName(String name);

    void deleteAllByBankName(String name);

    @Query(value = "select bank from Bank bank where bank.bankName=:name")
    Optional<Bank> findBankByName(@Param("name") String name);
}
