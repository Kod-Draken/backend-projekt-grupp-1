package se.yrgo.services.exceptions;

public class GymClassFullException extends RuntimeException {
    public GymClassFullException(String message) {
        super(message);
    }
}
