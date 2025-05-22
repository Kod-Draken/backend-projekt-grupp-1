package se.yrgo.services;

public class MemberIdNotFoundException extends RuntimeException {
    public MemberIdNotFoundException(String message) {
        super(message);
    }
}
