package se.yrgo.dataaccess;

import se.yrgo.domain.GymClass;

import java.util.List;

public interface GymClassDao {
    public void createGymClass(GymClass gymClass);
    public void updateGymClass(GymClass gymClass);
    public void deleteGymClass(GymClass gymClass);
    public GymClass getGymClassesByName(String name);
    public GymClass getGymClassesByInstructor(String instructorId);
    public List<GymClass> getAllGymClasses();


}
