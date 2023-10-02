package com.example.urbanmobility.account;
//import com.example.urbanmobility.entity.Order;
import com.example.urbanmobility.transportation.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, updatable = false)
    private String role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int paymentHistory;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private boolean isPaymentSet;

    @OneToMany(targetEntity = Transport.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="account_id",referencedColumnName = "id")
    private List<Transport> routes;

}