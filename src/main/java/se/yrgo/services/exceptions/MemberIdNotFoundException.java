package se.yrgo.services.exceptions;

public class MemberIdNotFoundException extends RuntimeException {
    public MemberIdNotFoundException(String message) {
        super(message);
    }
}
