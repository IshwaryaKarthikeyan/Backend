package com.hexa.amazecare.exception;

@SuppressWarnings("serial")
public class MedicalRecordNotFoundException extends RuntimeException {
    public MedicalRecordNotFoundException(String message) {
        super(message);
    }
}
