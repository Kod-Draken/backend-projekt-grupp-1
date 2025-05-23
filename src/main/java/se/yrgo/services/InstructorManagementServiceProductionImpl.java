package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.InstructorDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import java.util.List;

/**
 * @author Najib Bardash
 *
 * This class is a production-implementation of the InstructorManagementService-interface
 */
@Transactional
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
     * @param changedInstructor will be the new values for the existing instructor
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
     * @param instructorId of the instructor
     * @return the instructor with matching id
     */
    @Override
    public Instructor findInstructorById(String instructorId) {
        return instructorDao.getInstructorById(instructorId);
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
     * @param instructorId of instructor
     * @return all gym classes for instructors with matching id
     */
    @Override
    public List<GymClass> getGymClassesForInstructor(String instructorId) {
        return instructorDao.getGymClasses(instructorId);
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
