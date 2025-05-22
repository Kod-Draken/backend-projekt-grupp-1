package se.yrgo.services;

import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.InstructorDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 */
@Service
public class InstructorManagementServiceProductionImpl implements InstructorManagementService {
    private InstructorDao instructorDao;

    @Override
    public void newInstructor(Instructor instructor) {
        instructorDao.addInstructor(instructor);
    }

    @Override
    public void editInstructor(Instructor changedInstructor) {
        instructorDao.modifyInstructor(changedInstructor);
    }

    @Override
    public void deleteInstructor(Instructor deletedInstructor) {
        instructorDao.removeInstructor(deletedInstructor);
    }

    @Override
    public Instructor findInstructorById(String id) throws Exception {
        return instructorDao.getInstructorById(id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorDao.getAllInstructors();
    }

    @Override
    public List<Instructor> getInstructorsByName(String name) {
        return instructorDao.getInstructorsByName(name);
    }

    @Override
    public List<GymClass> getGymClassesForInstructor(String id) {
        return instructorDao.getGymClasses(id);
    }

    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        return instructorDao.getNumberOfClassesForInstructor(instructor);
    }
}
