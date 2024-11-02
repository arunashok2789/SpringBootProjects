package com.bankingmanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTO {
    private String accountNo;
    private String accountType;
    private String accountBalance;
}
