package com.example.urbanmobility.service;
import com.example.urbanmobility.dto.AccountDto;
import com.example.urbanmobility.entity.Account;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import com.example.urbanmobility.mapper.AccountDTOMapper;
import com.example.urbanmobility.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDTOMapper accountDTOMapper;

    public AccountService(AccountRepository accountRepository, AccountDTOMapper accountDTOMapper) {
        this.accountRepository = accountRepository;
        this.accountDTOMapper = accountDTOMapper;
    }

    public Optional<AccountDto> getAccountById(long accountId) {
        return accountRepository.findById(accountId)
                .map(accountDTOMapper);
    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountDTOMapper)
                .collect(Collectors.toList());

    }


    public Account createAccount(Account account) {
        // Check if username already exist
        String username = account.getUsername();
        if (accountRepository.findByUsername(username) != null) {
            throw new InvalidInputException("Username already exists");
        }
        // Check if email already exist
        String email = account.getEmail();
        if (accountRepository.findByEmail(email) != null) {
            throw new InvalidInputException("Email already exists");
        }
        // Save account to database
        return accountRepository.save(account);
    }

    public void deleteAccountById(long accountId) {
        if(!accountRepository.existsById(accountId)){
            throw new ResourceNotFoundException("Account with ID" + " " + accountId + " " + "does not exist");
        }
        accountRepository.deleteById(accountId);
    }

    public Account updateAccountById(Long accountId, Account account) {
        // Throw exception if account does not exist
        Account fetchedAccount = accountRepository.findById(accountId).orElseThrow(
                () -> new EntityNotFoundException("Account with" + accountId + "does not exist"));

        // Change account information
        fetchedAccount.setUsername(account.getUsername());
        fetchedAccount.setRole(account.getRole());
        fetchedAccount.setEmail(account.getEmail());
        fetchedAccount.setPhone(account.getPhone());
        fetchedAccount.setPaymentMethod(account.getPaymentMethod());
        fetchedAccount.setPaymentHistory(account.getPaymentHistory());
        fetchedAccount.setPaymentSet(account.isPaymentSet());

        // Save changes to database
        return accountRepository.save(fetchedAccount);
    }
}

