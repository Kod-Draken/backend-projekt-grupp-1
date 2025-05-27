package se.yrgo.services;

import se.yrgo.domain.GymClass;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;

import java.util.List;

/**
 * @author Mattiass
 */
public interface GymClassManagementService {

    public void addNewGymClass(GymClass newClass);

    public List<GymClass> getClassesByName(String gymClassName);

    public GymClass getClassById(String gymClassId);

    public void editGymClass(GymClass changedClass) throws AlreadyBookedToGymClassException, GymClassFullException;

    public void deleteGymClass(GymClass gymClass);

    public List<GymClass> getAllClasses();

}
