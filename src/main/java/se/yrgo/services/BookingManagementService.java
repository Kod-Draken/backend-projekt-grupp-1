package se.yrgo.services;

public interface BookingManagementService {

    public void addAttendantToClass(String gymClassId, String attendantId) throws GymClassFullException, AlreadyBookedToGymClassException;

    public void removeAttendantFromClass(String gymClassId, String attendantId);

    public void updateClassInstructor(int gymClassId, String instructorId);
}
