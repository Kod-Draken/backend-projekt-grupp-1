package se.yrgo.services;

import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;

import java.util.List;

/**
 * @author Mattias
 */
public interface GymClassManagementService {

    void addNewGymClass(GymClass newClass);

    List<GymClass> getClassesByName(String gymClassName);

    GymClass getClassById(String gymClassId);

    void editGymClass(GymClass changedClass) throws AlreadyBookedToGymClassException, GymClassFullException;

    void deleteGymClass(GymClass gymClass);

    List<GymClass> getAllClasses();

    List<Member> getMembersByGymClass(String gymClassId);

}
