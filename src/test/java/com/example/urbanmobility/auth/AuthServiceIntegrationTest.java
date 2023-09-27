package com.example.urbanmobility.auth;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.InvalidPermissionException;
import com.example.urbanmobility.exception.ResourceNotFoundException;
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
                .role("user")
                .email("Roder@example.com")
                .phone("08123456789")
                .paymentHistory(0)
                .paymentMethod("swish")
                .isPaymentSet(true)
                .build();
    }
    @Test
    public void ShouldThrowException_IfAccountIsNotASupplier(){
        // Arrange
        accountService.createAccount(account);
        account.setRole("user");
        // Act
        assertThrows(InvalidPermissionException.class,
                () -> authService.validSupplier(account.getId()));
    }

    @Test
    public void ShouldThrowException_IfAccountIdDoesNotExist(){
        // Arrange
        account.setRole("user");
        // Act
        assertThrows(ResourceNotFoundException.class,
                () -> authService.validSupplier(account.getId()));
    }

    @Test
    public void ShouldReturnValidSupplier_IfAccountIsASupplier(){
        // Arrange
        account.setRole("supplier");
        accountService.createAccount(account);

        // Act
        String result = authService.validSupplier(account.getId());
        // Assert
        assertThat(result).isEqualTo("You are a supplier!");
    }
}
