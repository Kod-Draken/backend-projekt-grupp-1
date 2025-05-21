package se.yrgo.services;

import se.yrgo.domain.GymClass;

import java.util.List;

public interface GymClassManagementService {

    public GymClass newGymClass(GymClass newClass);
    public GymClass changeGymClass(GymClass changeClassName);
    public GymClass deleteGymClass(GymClass gymClassName);
    public List<GymClass> getAllGymClasses();
}
