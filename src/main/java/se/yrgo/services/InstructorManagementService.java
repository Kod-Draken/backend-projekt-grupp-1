package se.yrgo.services;

import org.springframework.stereotype.Service;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 */
public interface InstructorManagementService {
    public void newInstructor(Instructor instructor);
    public void editInstructor(Instructor changedInstructor);
    public void deleteInstructor(Instructor deletedInstructor);
    public Instructor findInstructorById(String id) throws Exception;
    public List<Instructor> getAllInstructors();
    public List<Instructor> getInstructorsByName(String name);
    public List<GymClass> getGymClasses(String id);
    public int getNumberOfClassesForInstructor(Instructor instructor);
}
