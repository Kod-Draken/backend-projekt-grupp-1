package se.yrgo.services.exceptions;

/**
 * @author Mattias
 * Thrown when attempting to add a member to a class when the class attendant list is full,
 * or rather, the attendant lists length is equal to the gym class's capacity.
 */
public class GymClassFullException extends RuntimeException {
    public GymClassFullException(String message) {
        super(message);
    }
}
