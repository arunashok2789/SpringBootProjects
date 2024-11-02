package com.bankingmanagement.exception;

public class BranchDetailsNotFound extends Exception{
    public BranchDetailsNotFound() {
        super();
    }
    public BranchDetailsNotFound(String message) {
        super(message);
    }
}
