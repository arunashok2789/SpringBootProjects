package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.model.BranchTO;

import java.util.List;

public interface BranchService {
    List<BranchTO> findAll() throws BranchDetailsNotFound;
}
