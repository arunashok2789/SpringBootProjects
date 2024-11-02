package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BranchDetailsNotFound;
import com.bankingmanagement.exception.CustomerNotFoundException;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.service.CustomerService;
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
@RequestMapping("api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerTO>> getCustomers() throws CustomerNotFoundException {
       List<CustomerTO> customerTOS;
        try{
            customerTOS = customerService.findAll();
            log.info("Branch Details, customerTOs:{}", customerTOS);
        }catch (CustomerNotFoundException ex){
            log.error("Customer details not found", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.error("Exception while getting branch details", ex1);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      return new ResponseEntity<>(customerTOS,HttpStatus.OK);
    }
}
