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

    public void editGymClass(GymClass changedClass);

    public void deleteGymClass(GymClass gymClass);

    public List<GymClass> getAllClasses();

}
