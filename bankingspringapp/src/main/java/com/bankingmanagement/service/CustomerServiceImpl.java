package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.CustomerNotFoundException;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<CustomerTO> findAll() throws CustomerNotFoundException {
        List<Customer> customers = customerRepo.findAll();
        if(CollectionUtils.isEmpty(customers)){
            throw new CustomerNotFoundException("Customer Details not found");
        }
        List<CustomerTO> customerTOS = customers.stream().map(customer -> {
            CustomerTO customerTO = new CustomerTO();
            customerTO.setCustomerId(customer.getCustomerId());
            customerTO.setName(customer.getName());
            customerTO.setPhone(customer.getPhone());
            customerTO.setAddress(customer.getAddress());
            return customerTO;
        }).collect(Collectors.toList());
        return customerTOS;
    }
}
