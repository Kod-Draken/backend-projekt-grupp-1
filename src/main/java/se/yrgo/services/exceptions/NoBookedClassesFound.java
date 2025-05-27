package se.yrgo.services.exceptions;

/**
 * If no classes were found during check for class to cancel, throw exception.
 */
public class NoBookedClassesFound extends RuntimeException {
    public NoBookedClassesFound(String message) {
        super(message);
    }
}
