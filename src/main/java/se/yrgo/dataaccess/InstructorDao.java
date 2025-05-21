package se.yrgo.dataaccess;


import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 */
public interface InstructorDao {
    public void addInstructor(Instructor instructor);
    public void modifyInstructor(Instructor changedInstructor);
    public void removeInstructor(Instructor deletedInstructor);
    public Instructor getInstructorById(String id) throws Exception;
    public List<Instructor> getAllInstructors();
    public List<Instructor> getInstructorsByName(String name);
    public List<GymClass> getGymClasses(String id);
    public int getNumberOfClassesForInstructor(Instructor instructor);
}
