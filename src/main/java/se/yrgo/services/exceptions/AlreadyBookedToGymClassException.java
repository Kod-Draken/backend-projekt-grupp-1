package se.yrgo.services.exceptions;

public class AlreadyBookedToGymClassException extends Exception {
    public AlreadyBookedToGymClassException(String message)  {
        super(message);
    }
}
