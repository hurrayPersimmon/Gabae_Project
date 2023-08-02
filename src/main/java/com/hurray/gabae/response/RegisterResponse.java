package com.hurray.gabae.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse<T> {
    private boolean success;
    private int property;
    private String message;
    private boolean registered;


}
