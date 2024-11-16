package com.hexa.amazecare.exception;

@SuppressWarnings("serial")
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
