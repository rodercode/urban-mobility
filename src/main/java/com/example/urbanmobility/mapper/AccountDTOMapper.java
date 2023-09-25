//package com.example.urbanmobility.mapper;
//
//import com.example.urbanmobility.dto.AccountDto;
//import com.example.urbanmobility.account.Account;
//import org.springframework.stereotype.Service;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//
//
//@Service
//public class AccountDTOMapper implements Function<Account, AccountDto> {
//    @Override
//    public AccountDto apply(Account account) {
//        return new AccountDto(
//                account.getUsername(),
//                account.getRole(),
//                account.getEmail(),
//                account.getPhone()
//        );
//    }
//}
