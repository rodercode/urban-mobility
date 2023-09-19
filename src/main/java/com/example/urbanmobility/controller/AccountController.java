package com.example.urbanmobility.controller;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}
