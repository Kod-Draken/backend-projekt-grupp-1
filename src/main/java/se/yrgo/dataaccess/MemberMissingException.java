package se.yrgo.dataaccess;

public class MemberMissingException extends RuntimeException {
    public MemberMissingException(String message) {
        super(message);
    }
}
