package com.example.urbanmobility.model;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Route {
    private Long routeId;
    private String departure;
    private String arrival;
    private String transportType;
    private Date estDeparture;
    private Date estArrival;
    private double price;
    private String supplier;
}
