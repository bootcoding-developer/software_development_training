package com.bootcoding.restaurant.exceptions;

public class VendorServiceException extends Exception {

    public VendorServiceException(Exception ex) {
        super(ex);
    }

    public VendorServiceException(String message) {
        super(message);
    }

    public VendorServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
