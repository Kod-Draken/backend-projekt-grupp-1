package se.yrgo.dataaccess.exceptions;

/**
 * @author Mattias
 * Thrown when a specified gym class cannot be found in the database
 */
public class GymClassNotFoundException extends RuntimeException {
    public GymClassNotFoundException(String message) {
        super(message);
    }
}
