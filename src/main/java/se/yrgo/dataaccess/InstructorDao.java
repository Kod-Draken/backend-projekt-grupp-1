package se.yrgo.dataaccess;


import se.yrgo.dataaccess.exceptions.InstructorNotFoundException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 *
 * This interface is a Dao-layer for Instructor
 */
public interface InstructorDao {
    void addInstructor(Instructor instructor);
    void modifyInstructor(Instructor changedInstructor) throws InstructorNotFoundException;
    void removeInstructor(Instructor deletedInstructor) throws InstructorNotFoundException;
    Instructor getInstructorById(String instructorId) throws InstructorNotFoundException;
    List<Instructor> getAllInstructors();
    List<Instructor> getInstructorsByName(String name);
    List<GymClass> getGymClasses(String instructorId) throws InstructorNotFoundException;
    int getNumberOfClassesForInstructor(Instructor instructor);
}
