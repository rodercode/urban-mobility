package com.example.urbanmobility.account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public Optional<Account> getAccountById(@PathVariable("accountId") long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping()
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long accountId, @RequestBody Account account) {
        accountService.updateAccountById(accountId, account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") int accountId) {
        accountService.deleteAccountById(accountId);
        return new ResponseEntity<>("Account was deleted successfully", HttpStatus.OK);
    }


}
