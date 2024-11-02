package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.repository.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<AccountTO> findAll() throws AccountDetailsNotFound {
        List<Account> accounts = accountRepo.findAll();
        if(CollectionUtils.isEmpty(accounts)){
            log.error("Account details not found");
            throw new AccountDetailsNotFound("Account details not found");
        }

      List<AccountTO>  accountTOS = accounts.stream().map(account ->{
                    AccountTO accountTo = new AccountTO();
                    accountTo.setAccountType(account.getAccountType());
                    accountTo.setAccountNo(account.getAccountNo());
                    accountTo.setAccountBalance(account.getBalance());
                    return accountTo;
                }).collect(Collectors.toList());

        return accountTOS;
    }
}
