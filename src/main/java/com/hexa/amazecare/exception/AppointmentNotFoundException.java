package com.hexa.amazecare.exception;

@SuppressWarnings("serial")
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
