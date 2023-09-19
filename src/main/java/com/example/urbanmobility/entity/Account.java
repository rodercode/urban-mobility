package com.example.urbanmobility.entity;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "payment_history", nullable = false)
    private int paymentHistory;

    @Column(name = "credit_detail", nullable = false)
    private String creditDetail;

    @Column(name = "is_payment_set", nullable = false)
    private boolean isPaymentSet;
}