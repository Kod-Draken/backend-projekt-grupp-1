package se.yrgo.services;

import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattiass
 */
public interface GymClassManagementService {

    public void addNewGymClass(GymClass newClass);

    public List<GymClass> getClassesByName(String gymClassName);

    public GymClass getClassById(int gymClassId);

//    public void editGymClass(GymClass changedClass);

    public void addAttendantToClass(int gymClassId, int attendantId) throws GymClassFullException, AlreadyBookedToGymClassException;

    public void removeAttendantFromClass(int gymClassId, int attendantId);

    public void deleteGymClass(GymClass gymClassName);

    public List<GymClass> getAllClasses();

}
