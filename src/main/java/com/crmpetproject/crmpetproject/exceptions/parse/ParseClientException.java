package com.crmpetproject.crmpetproject.exceptions.parse;

public class ParseClientException extends Exception {

    public ParseClientException(String message) {
        super(message);
    }

    public ParseClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
