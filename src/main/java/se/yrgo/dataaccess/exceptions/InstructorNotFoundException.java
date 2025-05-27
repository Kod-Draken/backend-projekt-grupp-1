package se.yrgo.dataaccess.exceptions;

/**
 * Throws an exception if an instructor cannot be found in the database
 * @author Najib Bardash
 */
public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(String message) {
        super(message);
    }
}
