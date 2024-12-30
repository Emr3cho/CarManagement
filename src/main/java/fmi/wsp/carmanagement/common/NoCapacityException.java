package fmi.wsp.carmanagement.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoCapacityException extends RuntimeException {
    public NoCapacityException(String message) {
        super(message);
    }
}