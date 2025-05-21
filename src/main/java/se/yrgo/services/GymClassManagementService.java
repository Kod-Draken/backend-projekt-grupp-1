package se.yrgo.services;

import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattiass
 */
public interface GymClassManagementService {

    public void newGymClass(GymClass newClass);

    public GymClass getGymClassWithName(String gymClassName);

    public GymClass getGymClassWithId(int gymClassId);

    public void changeGymClass(GymClass changedClass);

    public void deleteGymClass(GymClass gymClassName);

    public List<GymClass> getAllGymClasses();

}
