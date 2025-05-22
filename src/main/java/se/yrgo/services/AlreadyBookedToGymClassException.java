package se.yrgo.services;

public class AlreadyBookedToGymClassException extends Exception {
    public AlreadyBookedToGymClassException(String message)  {
        super(message);
    }
}
