package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.repository.BankRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {
    @Mock
    private BankRepo bankRepository;
    @InjectMocks
    private BankServiceImpl bankService;

    @Test
    public void findAll_whenValidInput_thenReturnsBankDetails() throws BankDetailsNotFound {
        Bank bank = new Bank();
        bank.setBankCode("999");
        bank.setBankName("SBI");
        bank.setBankAddress("Mumbai");
        List<Bank> banks = new ArrayList<>();
        banks.add(bank);
        when(bankRepository.findAll()).thenReturn(banks);
        List<BankTO> bankDTOList = bankService.findAll();
        assertEquals(1, bankDTOList.size());
    }

    @Test
    public void findAll_whenValidInputNotExists_thenThrowException() {
        List<Bank> bankList = new ArrayList<>();
        when(bankRepository.findAll()).thenReturn(bankList);
        BankDetailsNotFound thrown = Assertions.assertThrows(BankDetailsNotFound.class, () -> {
            bankService.findAll();
        });
        assertEquals("Bank details not found", thrown.getMessage());
    }

    @Test
    public void findById_whenValidInput_thenReturnsBankDetails() throws BankDetailsNotFound {
        Bank bank = new Bank();
        bank.setId("1");
        bank.setBankCode("999");
        bank.setBankName("SBI");
        bank.setBankAddress("Mumbai");
        List<Bank> banks = new ArrayList<>();
        banks.add(bank);
        when(bankRepository.findById(anyString())).thenReturn(Optional.of(bank));
        BankTO bankTO = bankService.findBankDetails("1");
        assertEquals("SBI", bankTO.getBankName());
    }

    @Test
    public void findById_whenBankDetailsNotExists_thenThrowException() {
        List<Bank> bank = new ArrayList<>();
        when(bankRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        BankDetailsNotFound thrown = Assertions.assertThrows(BankDetailsNotFound.class, () -> {
            bankService.findBankDetails("1");
        });
        assertEquals("Bank details not found", thrown.getMessage());
    }
}
