package se.yrgo.services;

/**
 * @author Alrik, Mattias, Najib
 *
 * This interface is a service-layer that handles bookings
 */
@SuppressWarnings("unused")
public interface BookingManagementService {
    void addAttendantToClass(int gymClassId, String attendantId) throws GymClassFullException;
    void removeAttendantFromClass(int gymClassId, String attendantId);
    void updateClassInstructor(int gymClassId, String instructorId);
}
