package se.yrgo.services;

/**
 * @author Alrik, Mattias, Najib
 *
 * This interface is a service-layer that handles bookings
 */
@SuppressWarnings("unused")
public interface BookingManagementService {
    void addAttendantToClass(String gymClassId, String attendantId) throws GymClassFullException;
    void removeAttendantFromClass(String gymClassId, String attendantId);
    void updateClassInstructor(String gymClassId, String instructorId);
}
