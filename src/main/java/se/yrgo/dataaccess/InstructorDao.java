package se.yrgo.dataaccess;


import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 *
 * This interface is a Dao-layer for Instructor
 */
public interface InstructorDao {
    public void addInstructor(Instructor instructor);
    public void modifyInstructor(Instructor changedInstructor) throws InstructorNotFoundException;
    public void removeInstructor(Instructor deletedInstructor) throws InstructorNotFoundException;
    public Instructor getInstructorById(String id) throws InstructorNotFoundException;
    public List<Instructor> getAllInstructors();
    public List<Instructor> getInstructorsByName(String name);
    public List<GymClass> getGymClasses(String id) throws InstructorNotFoundException;
    public int getNumberOfClassesForInstructor(Instructor instructor);
}
