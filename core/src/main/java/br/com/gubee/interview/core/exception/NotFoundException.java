package br.com.gubee.interview.core.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

}
