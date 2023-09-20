package com.example.urbanmobility.service;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
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

    public void deleteAccountById(long accountId) {
        if (!accountRepository.existsById(accountId)){
            throw new EntityNotFoundException("Account with" + accountId + "does not exist");
        }
        accountRepository.deleteById(accountId);
    }

    public Account updateAccountById(Long accountId, Account account) {
        // Throw exception if account does not exist
        if (!accountRepository.existsById(accountId)) {
            throw new EntityNotFoundException("Account with" + account.getId() + "does not exist");
        }

        // Change account information
        Account fetchedAccount = accountRepository.findById(accountId).get();
        fetchedAccount.setUsername(account.getUsername());
        fetchedAccount.setEmail(account.getEmail());
        fetchedAccount.setPhone(account.getPhone());
        fetchedAccount.setPaymentMethod(account.getPaymentMethod());
        fetchedAccount.setPaymentHistory(account.getPaymentHistory());
        fetchedAccount.setPaymentSet(account.isPaymentSet());
        fetchedAccount.setRole(account.getRole());

        // Save changes to database
        return accountRepository.save(fetchedAccount);
    }
}
