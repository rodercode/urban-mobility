package com.example.urbanmobility.auth;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.InvalidInputException;

public class AuthService {

    private AccountService accountService;

    public AuthService(AccountService accountService) {
        this.accountService = accountService;
    }


    public String validSupplier(long accountId) {
        Account supplier = accountService.getAccountById(accountId).get();
        if (!supplier.getRole().equals("supplier")) {
            throw new InvalidInputException("You are not a supplier!");
        }
        return "You are a supplier!";
    }
}




