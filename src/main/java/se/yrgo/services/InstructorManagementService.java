package se.yrgo.services;

import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 * This interface is a Service-layer between the Client and the Dao-layer to
 * execute tasks regarding instructors
 */
@SuppressWarnings("unused")
public interface InstructorManagementService {
    void newInstructor(Instructor instructor);
    void editInstructor(Instructor changedInstructor);
    void deleteInstructor(Instructor deletedInstructor);
    Instructor findInstructorById(String instructorId);
    List<Instructor> getAllInstructors();
    List<Instructor> getInstructorsByName(String name);
    List<GymClass> getGymClassesForInstructor(String instructorId);
    int getNumberOfClassesForInstructor(Instructor instructor);
}
