package se.yrgo.services;

import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.domain.GymClass;

import java.util.List;

/**
 * @author Mattias
 */
public class GymClassManagementProdImpl implements GymClassManagementService{
    private GymClassDao gymClassDao;

    public GymClassManagementProdImpl(GymClassDao gymClassDao) {
        this.gymClassDao = gymClassDao;
    }

    @Override
    public void createNewGymClass(GymClass newClass) {
        gymClassDao.createGymClass(newClass);
    }

    @Override
    public List<GymClass> getClassesByName(String gymClassName) {
        return gymClassDao.getGymClassesByName(gymClassName);
    }

    @Override
    public GymClass getClassById(int gymClassId) {
        return gymClassDao.getGymClassById(gymClassId);
    }

    @Override
    public void editGymClass(GymClass changedClass) {
        gymClassDao.updateGymClass(changedClass);
    }

    @Override
    public void deleteGymClass(GymClass gymClassName) {
        gymClassDao.deleteGymClass(gymClassName);
    }

    @Override
    public List<GymClass> getAllClasses() {
        return gymClassDao.getAllGymClasses();
    }
}
