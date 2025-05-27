package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;

import java.util.List;

/**
 * @author Mattias
 * Implementation of GymClassManagementService, uses an instance of a gymClassDao to send data
 * from the entity-class to the database.
 */
@Transactional
@Service
public class GymClassManagementProdImpl implements GymClassManagementService{
    private final GymClassDao gymClassDao;

    @Autowired
    public GymClassManagementProdImpl(GymClassDao gymClassDao) {
        this.gymClassDao = gymClassDao;
    }

    /**
     * Sends a new class to the DAO
     * @param newClass is a new class to be added to the database
     */
    @Override
    public void addNewGymClass(GymClass newClass) {
        gymClassDao.createGymClass(newClass);
    }

    /**
     * Get a list of all classes matching a name pattern
     * @param gymClassName is  a pattern that will be run against the database
     * @return List of GymClass that matched the search pattern
     */
    @Override
    public List<GymClass> getClassesByName(String gymClassName) {
        return gymClassDao.getGymClassesByName(gymClassName);
    }

    /**
     * Gets a gymClass by id.
     * @param gymClassId is a unique identifier that autogenerates
     * @return a matching GymClass
     */
    @Override
    public GymClass getClassById(String gymClassId) {
        return gymClassDao.getGymClassById(gymClassId);
    }

    /**
     * Send updated information of an existing gymClass to the DAO
     * @param updatedClass A gymClass that has been modified
     */
    @Override
    public void editGymClass(GymClass updatedClass) throws AlreadyBookedToGymClassException, GymClassFullException {
        gymClassDao.updateGymClass(updatedClass);
    }

    /**
     * Remove a gymClass from the database
     * @param gymClassToDelete is the gymClass to be deleted
     */
    @Override
    public void deleteGymClass(GymClass gymClassToDelete) {
        gymClassDao.deleteGymClass(gymClassToDelete);
    }

    /**
     * Get all classes
     * @return a List containing all the classes in the database
     */
    @Override
    public List<GymClass> getAllClasses() {
        return gymClassDao.getAllGymClasses();
    }

    /**
     * Gets all members for a gym class
     * @param gymClassId is the id of the class
     * @return a list of members for a class
     */
    @Override
    public List<Member> getMembersByGymClass(String gymClassId) {
        return gymClassDao.getAllAttendants(gymClassId);
    }
}
