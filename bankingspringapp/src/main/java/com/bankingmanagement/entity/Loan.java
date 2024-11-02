package com.bankingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "loan")
public class Loan {
    @Id
    private String id;
    @Field(name = "loan_id")
    private String loanId;
    @Field(name = "loan_type")
    private String loanType;
    @Field(name = "amount")
    private String loanAmount;
}
