package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.dataaccess.MemberDao;
import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattias
 */
@Service
public class GymClassManagementProdImpl implements GymClassManagementService{
    private GymClassDao gymClassDao;
    private MemberDao memberDao;

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
    public GymClass getClassById(int gymClassId) {
        return gymClassDao.getGymClassById(gymClassId);
    }

    /**
     * Send updated information of an existing gymClass to the DAO
     * @param changedClass A gymClass that has been modified
     */
    @Override
    public void editGymClass(GymClass changedClass) {
        gymClassDao.updateGymClass(changedClass);
    }

    /**
     * Remove a gymClass from the database
     * @param gymClass is the gymClass to be deleted
     */
    @Override
    public void deleteGymClass(GymClass gymClass) {
        gymClassDao.deleteGymClass(gymClass);
    }

    /**
     * Get all classes
     * @return a List containing all the classes in the database
     */
    @Override
    public List<GymClass> getAllClasses() {
        return gymClassDao.getAllGymClasses();
    }
}
