package com.osama.learningspringboot.model;

public class ErrorMessage {
    private final String message;

    public String getMessage() {
        return message;
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }

}

