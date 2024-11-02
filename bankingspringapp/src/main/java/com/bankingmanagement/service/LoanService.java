package com.bankingmanagement.service;

import com.bankingmanagement.exception.LoanNotFoundException;
import com.bankingmanagement.model.LoanTO;

import java.util.List;

public interface LoanService {
    List<LoanTO> findAll() throws LoanNotFoundException;
}
