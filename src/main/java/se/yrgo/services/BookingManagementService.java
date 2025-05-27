package se.yrgo.services;

import se.yrgo.domain.GymClass;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;
import se.yrgo.services.exceptions.LateCancelException;

import java.util.List;

/**
 * @author Alrik, Mattias, Najib
 *
 * This interface is a service-layer that handles bookings
 */
@SuppressWarnings("unused")
public interface BookingManagementService {
    void addAttendantToClass(String gymClassId, String attendantId) throws GymClassFullException, AlreadyBookedToGymClassException;
    void removeAttendantFromClass(String gymClassId, String attendantId) throws LateCancelException;
    void updateClassInstructor(String gymClassId, String instructorId);
    List<GymClass> bookingCheck(String memberId);

}
