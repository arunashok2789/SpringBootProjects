package com.bankingmanagement.service;

import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.LoanNotFoundException;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.repository.LoanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService{
    @Autowired
    private LoanRepo loanRepo;

    @Override
    public List<LoanTO> findAll() throws LoanNotFoundException {
      List<Loan> loans = loanRepo.findAll();
      if(CollectionUtils.isEmpty(loans)){
          throw new LoanNotFoundException("Loans are not found");
      }
    List<LoanTO> loanTOS =  loans.stream().map(loan -> {
          LoanTO loanTO = new LoanTO();
          loanTO.setLoanAmount(loan.getLoanAmount());
          loanTO.setLoanType(loan.getLoanType());
          loanTO.setLoanId(loan.getLoanId());
          return loanTO;
      }).collect(Collectors.toList());
        return loanTOS ;
    }
}
