package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.InstructorDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 *
 * This class is a production-implementation of the InstructorManagementService-interface
 */
@Service
public class InstructorManagementServiceProductionImpl implements InstructorManagementService {
    private final InstructorDao instructorDao;

    @Autowired
    public InstructorManagementServiceProductionImpl(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    /**
     *
     * @param instructor to be added
     */
    @Override
    public void newInstructor(Instructor instructor) {
        instructorDao.addInstructor(instructor);
    }

    /**
     *
     * @param changedInstructor will be modified
     */
    @Override
    public void editInstructor(Instructor changedInstructor) {
        instructorDao.modifyInstructor(changedInstructor);
    }

    /**
     *
     * @param deletedInstructor to be removed
     */
    @Override
    public void deleteInstructor(Instructor deletedInstructor) {
        instructorDao.removeInstructor(deletedInstructor);
    }

    /**
     *
     * @param id of the instructor
     * @return the instructor with matching id
     */
    @Override
    public Instructor findInstructorById(String id) {
        return instructorDao.getInstructorById(id);
    }

    /**
     *
     * @return all instructors
     */
    @Override
    public List<Instructor> getAllInstructors() {
        return instructorDao.getAllInstructors();
    }

    /**
     *
     * @param name of the instructor to be found
     * @return all instructors with a matching name
     */
    @Override
    public List<Instructor> getInstructorsByName(String name) {
        return instructorDao.getInstructorsByName(name);
    }

    /**
     *
     * @param id of instructor
     * @return all gym classes for instructors with matching id
     */
    @Override
    public List<GymClass> getGymClassesForInstructor(String id) {
        return instructorDao.getGymClasses(id);
    }

    /**
     *
     * @param instructor to find gym classes for
     * @return number of classes that the matching instructor has
     */
    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        return instructorDao.getNumberOfClassesForInstructor(instructor);
    }
}
