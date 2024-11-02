package com.bankingmanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTO {
    private String loanId;
    private String loanType;
    private String loanAmount;
}
