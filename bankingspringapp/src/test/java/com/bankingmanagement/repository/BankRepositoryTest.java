
package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@RunWith(SpringRunner.class)
//@DataJpaTest
@DataMongoTest
public class BankRepositoryTest {
    @Autowired
    private BankRepo bankRepository;

    public void save(){
        Bank bank = new Bank();
        bank.setBankName("SBI");
        bank.setBankAddress("Bangalore");
        bankRepository.save(bank);
    }
    @Test
    public void findByBankName_whenValidBankName_thenReturnBankDetails(){
        save();
        List<Bank> bankOptional = bankRepository.findAll();
        assertEquals("SBI", bankOptional.get(0).getBankName());
        assertEquals("Bangalore", bankOptional.get(0).getBankAddress());
    }
}

