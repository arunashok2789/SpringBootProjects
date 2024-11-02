package com.bankingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "account")
public class Account {
    @Id
    private String id;
    @Field(name = "account_no")
    private String accountNo;
    @Field(name = "account_type")
    private String accountType;
    @Field(name = "balance")
    private String balance;
}
