package se.yrgo.dataaccess;

import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattias
 */
public interface GymClassDao {
    public void createGymClass(GymClass gymClass);
    public void updateGymClass(GymClass gymClass) throws GymClassNotFoundException;
    public void deleteGymClass(GymClass gymClass) throws GymClassNotFoundException;
    public GymClass getGymClassById(String id) throws GymClassNotFoundException;
    public List<GymClass> getGymClassesByName(String name);
    public List<GymClass> getGymClassesByInstructor(String instructorId);
    public List<GymClass> getAllGymClasses();


}
