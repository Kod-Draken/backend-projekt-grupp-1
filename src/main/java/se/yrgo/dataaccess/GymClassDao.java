package se.yrgo.dataaccess;

import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattias
 */
public interface GymClassDao {
    public void createGymClass(GymClass gymClass);
    public void updateGymClass(GymClass gymClass);
    public void deleteGymClass(GymClass gymClass);
    public GymClass getGymClassById(int id);
    public List<GymClass> getGymClassesByName(String name);
    public List<GymClass> getGymClassesByInstructor(String instructorId);
    public List<GymClass> getAllGymClasses();


}
