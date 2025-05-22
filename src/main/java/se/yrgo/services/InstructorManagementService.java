package se.yrgo.services;

import org.springframework.stereotype.Service;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 *
 * This interface is a Service-layer between the Client and the Dao-layer to
 * execute tasks regarding instructors
 */
public interface InstructorManagementService {
    public void newInstructor(Instructor instructor);
    public void editInstructor(Instructor changedInstructor);
    public void deleteInstructor(Instructor deletedInstructor);
    public Instructor findInstructorById(String id);
    public List<Instructor> getAllInstructors();
    public List<Instructor> getInstructorsByName(String name);
    public List<GymClass> getGymClassesForInstructor(String id);
    public int getNumberOfClassesForInstructor(Instructor instructor);
}
