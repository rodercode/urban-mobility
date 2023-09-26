package com.example.urbanmobility.auth;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.InvalidPermissionException;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private AccountService accountService;

    public AuthService(AccountService accountService) {
        this.accountService = accountService;
    }

    public String validSupplier(long accountId) {
        Optional<Account> supplier = accountService.getAccountById(accountId);
        if (supplier.isEmpty()){
            throw new ResourceNotFoundException("Account with id " + accountId + " not found!");

        }

        if (!supplier.get().getRole().equals("supplier")) {
            throw new InvalidPermissionException("You are not a supplier!");
        }
        return "You are a supplier!";
    }
}




