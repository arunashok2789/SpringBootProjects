package com.bankingmanagement.controller;

import com.bankingmanagement.exception.AccountDetailsNotFound;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountTO>> getAllAccounts(){
        log.info("Inside the AccountControllerV.getAllAccounts");

        List<AccountTO> accountTOS;

        try{
            accountTOS = accountService.findAll();
            log.info("Account Details, accountTOs:{}", accountTOS);
        }catch (AccountDetailsNotFound ex){
            log.error("Account details not found", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.error("Exception while getting Account details", ex1);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of the AccountControllerV2.getAllAccounts");
        return  new ResponseEntity<>(accountTOS, HttpStatus.OK);
    }
}
