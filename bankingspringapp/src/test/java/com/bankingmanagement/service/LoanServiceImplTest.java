package com.bankingmanagement.service;

import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.LoanNotFoundException;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.repository.LoanRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {
    @Mock
    LoanRepo loanRepository;

    @InjectMocks
    LoanServiceImpl loanService;

    @Test
    public void testFindAllLoans() throws LoanNotFoundException {
        Loan loan = new Loan();
        loan.setLoanId("7");
        loan.setLoanAmount("4000");
        loan.setLoanType("Bike Loan");

        List<Loan> loans = new ArrayList<>();
        loans.add(loan);

        when(loanRepository.findAll()).thenReturn(loans);
        List<LoanTO> loanDTOList = loanService.findAll();
        assertEquals(1, loanDTOList.size());
    }

    @Test()
    public void testFindAllLoansWithEmptyData() throws LoanNotFoundException {
        when(loanRepository.findAll()).thenReturn(null);
        List<LoanTO> loanDTOList = loanService.findAll();
        assertNull(loanDTOList);
    }
}
