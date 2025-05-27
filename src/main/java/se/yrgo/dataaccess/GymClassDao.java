package se.yrgo.dataaccess;

import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import java.util.List;

/**
 * @author Mattias
 */
public interface GymClassDao {
    void createGymClass(GymClass gymClass);
    void updateGymClass(GymClass gymClass) throws GymClassNotFoundException;
    void deleteGymClass(GymClass gymClass) throws GymClassNotFoundException;
    GymClass getGymClassById(String id) throws GymClassNotFoundException;
    List<GymClass> getGymClassesByName(String name);
    List<GymClass> getGymClassesByInstructor(String instructorId);
    List<GymClass> getAllGymClasses();
    List<Member> getAllAttendants(String gymClassId);
}
