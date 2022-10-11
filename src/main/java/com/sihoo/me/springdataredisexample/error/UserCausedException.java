package com.sihoo.me.springdataredisexample.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserCausedException extends RuntimeException{
    public UserCausedException(String message) {
        super(message);
    }
}
