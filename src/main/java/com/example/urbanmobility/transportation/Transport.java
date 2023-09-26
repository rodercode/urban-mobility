package com.example.urbanmobility.transportation;
import com.example.urbanmobility.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "routes")

public class Transport {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @Column(nullable = false)
    private String transportType;

    @Column(nullable = false)
    private String estDeparture;

    @Column(nullable = false)
    private String estArrival;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String supplier;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="account_id",referencedColumnName = "id")
    private Account account;
}
