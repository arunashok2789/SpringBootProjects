package com.bankingmanagement.exception;

public class AccountDetailsNotFound extends Exception{

    public AccountDetailsNotFound() {
        super();
    }

    public AccountDetailsNotFound(String message) {
        super(message);
    }
}
