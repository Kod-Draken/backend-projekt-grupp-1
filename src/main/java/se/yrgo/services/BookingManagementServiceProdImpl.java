package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.dataaccess.InstructorDao;
import se.yrgo.dataaccess.MemberDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;

/**
 * @author Alrik, Mattias, Najib
 *
 * This class represents a booking-service for gym classes
 */
@Transactional
@Service
public class BookingManagementServiceProdImpl implements BookingManagementService {
    private final GymClassDao gymClassDao;
    private final MemberDao memberDao;
    private final InstructorDao instructorDao;

    @Autowired
    public BookingManagementServiceProdImpl(GymClassDao gymClassDao, MemberDao memberDao, InstructorDao instructorDao) {
        this.gymClassDao = gymClassDao;
        this.memberDao = memberDao;
        this.instructorDao = instructorDao;
    }

    /**
     * Adds an attendee to a class
     * @param gymClassId is the id of the gym class
     * @param attendantId is the id of the member that wants to attend
     * @throws GymClassFullException is thrown if the class is full
     */
    @Override
    public void addAttendantToClass(int gymClassId, String attendantId) throws GymClassFullException {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Member newAttendant = memberDao.getById(attendantId);

        if (gymClass.isFull()) {
            throw new GymClassFullException("Sorry, class is fully booked!");
        }

        gymClass.addAttendant(newAttendant);

        System.out.println("Successfully added attendant " + newAttendant.toString());
    }

    /**
     * Removes an attendant from a gym class
     * @param gymClassId is the id of the gym class
     * @param attendantId is the id of the member that will be removed
     */
    @Override
    public void removeAttendantFromClass(int gymClassId, String attendantId) {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Member member = memberDao.getById(attendantId);
        gymClass.removeAttendant(member);
    }

    /**
     * Updates which instructor a class will have
     * @param gymClassId is the is of the gym class
     * @param newInstructorId is the id of the new instructor
     */
    @Override
    public void updateClassInstructor(int gymClassId, String newInstructorId) {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Instructor newInstructor = instructorDao.getInstructorById(newInstructorId);

        gymClass.setInstructor(newInstructor);

        gymClassDao.updateGymClass(gymClass);
    }
}
