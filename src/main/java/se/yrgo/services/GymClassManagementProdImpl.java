package se.yrgo.services;

import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.domain.GymClass;

import java.util.List;

public class GymClassManagementProdImpl implements GymClassManagementService{
    private GymClassDao gymClassDao;

    public GymClassManagementProdImpl(GymClassDao gymClassDao) {
        this.gymClassDao = gymClassDao;
    }

    @Override
    public void newGymClass(GymClass newClass) {
        gymClassDao.createGymClass(newClass);
    }

    @Override
    public GymClass getGymClassWithName(String gymClassName) {
        return gymClassDao.getGymClassesByName(gymClassName);
    }

    @Override
    public GymClass getGymClassWithId(int gymClassId) {
        return gymClassDao.getGymClassById(gymClassId);
    }

    @Override
    public void changeGymClass(GymClass changedClass) {
        gymClassDao.updateGymClass(changedClass);
    }

    @Override
    public void deleteGymClass(GymClass gymClassName) {
        gymClassDao.deleteGymClass(gymClassName);
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return gymClassDao.getAllGymClasses();
    }
}
