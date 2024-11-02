package com.bankingmanagement.controller;

import com.bankingmanagement.exception.LoanNotFoundException;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.service.LoanService;
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
@RequestMapping("api/v1/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanTO>> getLoans() throws LoanNotFoundException {
        List<LoanTO> loanTOS;
        try {
            loanTOS = loanService.findAll();

        } catch (LoanNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loanTOS, HttpStatus.OK);
    }
}
