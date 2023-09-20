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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int paymentHistory;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private boolean isPaymentSet;

    public void setRole(String role) {
        if(this.role != role){
            throw new IllegalArgumentException("Role cannot be changed");
        }
        this.role = role;
    }

    public void setPaymentSet(boolean paymentSet) {
        isPaymentSet = paymentSet;
    }

    public boolean isPaymentSet() {
        return isPaymentSet;
    }
}