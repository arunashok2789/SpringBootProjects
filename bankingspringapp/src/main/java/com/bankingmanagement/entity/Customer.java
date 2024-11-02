package com.bankingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    @Field(name = "cust_id")
    private String customerId;
    @Field(name = "name")
    private String name;
    @Field(name = "phone")
    private String phone;
    @Field(name = "address")
    private String address;
}
