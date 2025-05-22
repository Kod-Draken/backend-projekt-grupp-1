package se.yrgo.services;

public interface BookingManagementService {

    public void addAttendantToClass(int gymClassId, String attendantId) throws GymClassFullException, AlreadyBookedToGymClassException;

    public void removeAttendantFromClass(int gymClassId, String attendantId);

    public void updateClassInstructor(int gymClassId, String instructorId);
}
