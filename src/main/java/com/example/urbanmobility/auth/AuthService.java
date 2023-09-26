package com.example.urbanmobility.auth;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.InvalidPermissionException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AccountService accountService;

    public AuthService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void validSupplier(long accountId) {
        Account supplier = accountService.getAccountById(accountId).get();
        if (!supplier.getRole().equals("supplier")) {
            throw new InvalidPermissionException("You are not a supplier!");
        }
    }
}




