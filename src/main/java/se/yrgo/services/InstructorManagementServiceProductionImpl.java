package se.yrgo.services;

import org.springframework.stereotype.Service;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

@Service
public class InstructorManagementServiceProductionImpl implements InstructorManagementService {


    @Override
    public void newInstructor(Instructor instructor) {

    }

    @Override
    public void editInstructor(Instructor changedInstructor) {

    }

    @Override
    public void deleteInstructor(Instructor deletedInstructor) {

    }

    @Override
    public Instructor findInstructorById(String id) throws Exception {
        return null;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return List.of();
    }

    @Override
    public List<Instructor> getInstructorsByName(String name) {
        return List.of();
    }

    @Override
    public List<GymClass> getGymClasses(String id) {
        return List.of();
    }

    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        return 0;
    }
}
