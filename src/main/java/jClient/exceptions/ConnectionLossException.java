package jClient.exceptions;

public class ConnectionLossException extends Exception {
    public ConnectionLossException(String errorMessage) {
        super(errorMessage);
    }
}