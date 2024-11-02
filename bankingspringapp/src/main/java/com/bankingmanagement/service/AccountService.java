package com.bankingmanagement.service;

import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountTO;

import java.util.List;

public interface AccountService {
    List<AccountTO> findAll() throws AccountDetailsNotFound;
}
