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
    private String departure;
    private String arrival;
    private String transportType;
    private String estDeparture;
    private String estArrival;
    private double price;
    private String supplier;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="account_id",referencedColumnName = "id")
    private Account account;
}
