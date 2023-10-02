package com.example.urbanmobility.model;
import lombok.*;
import java.time.ZonedDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRes {
    private int status;
    private String message;
    private ZonedDateTime date;
    // Hello
}