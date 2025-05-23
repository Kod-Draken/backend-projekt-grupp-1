package se.yrgo.dataaccess.exceptions;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(String message) {
        super(message);
    }
}
