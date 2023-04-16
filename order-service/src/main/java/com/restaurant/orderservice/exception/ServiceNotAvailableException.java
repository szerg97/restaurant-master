package com.restaurant.orderservice.exception;

public class ServiceNotAvailableException extends RuntimeException{
    public ServiceNotAvailableException(String message) {
        super(message);
    }
}
