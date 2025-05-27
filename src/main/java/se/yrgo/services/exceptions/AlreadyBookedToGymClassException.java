package se.yrgo.services.exceptions;

/**
 * @author Mattias
 * Thrown when trying to book a member to a class they are already booked to
 */
public class AlreadyBookedToGymClassException extends Exception {
    public AlreadyBookedToGymClassException(String message)  {
        super(message);
    }
}
