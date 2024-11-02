package com.bankingmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "branch")
public class Branch {
    @Id
    private String id;
    @Field(name="branch_id")
    private String branchId;
    @Field(name = "branch_name")
    private String branchName;
    @Field(name = "branch_address")
    private String branchAddress;
}
