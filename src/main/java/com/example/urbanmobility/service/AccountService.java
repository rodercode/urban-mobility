package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        // Check if username already exist
        String username = account.getUsername();
        if (accountRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exist
        String email = account.getEmail();
        if(accountRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("Email already exists");
        }

        // Save account to database
        return accountRepository.save(account);
    }
}
