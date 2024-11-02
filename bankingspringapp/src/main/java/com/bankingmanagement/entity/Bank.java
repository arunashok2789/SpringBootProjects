package com.bankingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "bank")
public class Bank {
    @Id
    private String id;
    @Field(name ="bank_name")
    private String bankName;
   @Field(name ="bank_code")
    private String bankCode;
    @Field(name ="bank_address")
    private String bankAddress;
    @DBRef
    private List<Branch> branches;
}
