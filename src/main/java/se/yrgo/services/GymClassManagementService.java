package se.yrgo.services;

import se.yrgo.domain.GymClass;

import java.util.List;

public interface GymClassManagementService {

    public GymClass newGymClass(GymClass newClass);

    public GymClass getGymClassWithName(String gymClassName);

    public GymClass getGymClassWithId(int gymClassId);

    public GymClass changeGymClass(GymClass changedClass);

    public GymClass deleteGymClass(GymClass gymClassName);

    public List<GymClass> getAllGymClasses();

}
