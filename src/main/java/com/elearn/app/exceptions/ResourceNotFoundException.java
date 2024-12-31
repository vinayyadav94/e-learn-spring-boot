package com.elearn.app.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String courseNotFound) {
        super(courseNotFound);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
