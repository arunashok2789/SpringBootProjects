package com.bankingmanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTO {
    private String customerId;
    private String name;
    private String phone;
    private String address;
}
