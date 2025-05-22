package se.yrgo.services;

import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.dataaccess.MemberDao;
import se.yrgo.dataaccess.MemberDaoImpl;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import java.util.List;

/**
 * @author Mattias
 */
public class GymClassManagementProdImpl implements GymClassManagementService{
    private GymClassDao gymClassDao;
    private MemberDao memberDao;

    public GymClassManagementProdImpl(GymClassDao gymClassDao) {
        this.gymClassDao = gymClassDao;
    }

    @Override
    public void addNewGymClass(GymClass newClass) {
        gymClassDao.createGymClass(newClass);
    }

    @Override
    public List<GymClass> getClassesByName(String gymClassName) {
        return gymClassDao.getGymClassesByName(gymClassName);
    }

    /**
     *
     * @param gymClassId is a unique identifier that autogenerates
     * @return a matching GymClass
     */
    @Override
    public GymClass getClassById(int gymClassId) {
        return gymClassDao.getGymClassById(gymClassId);
    }

//    @Override
//    public void editGymClass(GymClass changedClass) {
//        gymClassDao.updateGymClass(changedClass);
//    }


    @Override
    public void deleteGymClass(GymClass gymClassName) {
        gymClassDao.deleteGymClass(gymClassName);
    }

    @Override
    public List<GymClass> getAllClasses() {
        return gymClassDao.getAllGymClasses();
    }
}
