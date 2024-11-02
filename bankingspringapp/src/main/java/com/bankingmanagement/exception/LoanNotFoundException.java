package com.bankingmanagement.exception;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException() {
        super();
    }

    public LoanNotFoundException(String message) {
        super(message);
    }
}
