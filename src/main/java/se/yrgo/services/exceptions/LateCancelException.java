package se.yrgo.services.exceptions;

/**
 * Occurs when trying to cancel a booking to a class when its due in less than 2 hours.
 * A layer of "realism", though in reality the occurrence of this would certainly depend
 * on what role the DB user has.
 */
public class LateCancelException extends RuntimeException {
    public LateCancelException(String message) {
        super(message);
    }
}
