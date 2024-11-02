package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BankTO;

import java.util.List;

public interface BankService {
    List<BankTO> findAll() throws BankDetailsNotFound;

    BankTO findBankDetails(String code) throws BankDetailsNotFound;
    BankTO findBankByName(String name) throws BankDetailsNotFound;
    BankTO save(BankRequest bankRequest) throws BankDetailsNotFound;
    String delete(String bankCode) throws BankDetailsNotFound;
}
