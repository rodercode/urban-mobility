package com.example.urbanmobility.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorRes {
    private int status;
    private String message;
    private ZonedDateTime date;
    public ErrorRes(int status) {
        this.status = status;
    }
}