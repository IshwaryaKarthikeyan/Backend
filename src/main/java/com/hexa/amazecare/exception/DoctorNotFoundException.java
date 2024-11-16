package com.hexa.amazecare.exception;

@SuppressWarnings("serial")
public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
