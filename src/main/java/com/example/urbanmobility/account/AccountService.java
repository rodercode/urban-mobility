package com.example.urbanmobility.account;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.exception.InvalidInputException;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import com.example.urbanmobility.account.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccountById(long accountId) {
        if (!accountRepository.existsById(accountId)){
            throw new ResourceNotFoundException("Account with ID" + " " + accountId + " " + "does not exist");
        }
        return accountRepository.findById(accountId);
    }

    public List<Account> getAllAccounts() {
        if(accountRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No account found");
        }
        return accountRepository.findAll();
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
        if (!accountRepository.existsById(accountId)){
            throw new ResourceNotFoundException("Account with ID" + " " + accountId + " " + "does not exist");
        }
        account.setId(accountId);
        return accountRepository.save(account);
    }
}
