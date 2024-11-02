package com.bankingmanagement.service;

import com.bankingmanagement.exception.CustomerNotFoundException;
import com.bankingmanagement.model.CustomerTO;

import java.util.List;

public interface CustomerService {
    List<CustomerTO> findAll() throws CustomerNotFoundException;
}
