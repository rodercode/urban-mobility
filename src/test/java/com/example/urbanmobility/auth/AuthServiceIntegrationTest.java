package com.example.urbanmobility.account;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountRepository;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.auth.AuthService;
import com.example.urbanmobility.exception.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountService accountService;

    // Variables
    private Account account;

    @BeforeEach
    public void setup(){
        // Previous object
        account = Account.builder()
                .id(1L)
                .username("Roder")
                .role("supplier")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();


    }
    @Test
    public void test(){
        // Arrange
        accountService.createAccount(account);

        // Act
        authService.validSupplier(account.getId());
    }
}
