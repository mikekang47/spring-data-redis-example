package com.sihoo.me.springdataredisexample.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class UserCausedException extends RuntimeException {
    private HttpStatus status;

    public UserCausedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
