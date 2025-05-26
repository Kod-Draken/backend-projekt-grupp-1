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
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;
import se.yrgo.services.exceptions.LateCancelException;

import java.time.Duration;
import java.time.LocalDateTime;

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
     * @throws AlreadyBookedToGymClassException if a member is already in the attendants list of the class.
     * @throws GymClassFullException is thrown when trying to book to a full class
     */
    @Override
    public void addAttendantToClass(String gymClassId, String attendantId) throws GymClassFullException, AlreadyBookedToGymClassException {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);

        if (gymClass.getAttendants().contains(memberDao.getById(attendantId))) {
            throw new AlreadyBookedToGymClassException("You're already booked to this class!");
        }

        if (gymClass.isFull()) {
            throw new GymClassFullException("Sorry, class is fully booked!");
        }
        Member newAttendant = memberDao.getById(attendantId);
        memberDao.addGymClass();

        System.out.println("Successfully added attendant " + newAttendant.toString() + " to class " + gymClass.toString());
    }

    /**
     * Removes an attendant from a gym class
     * @param gymClassId is the id of the gym class
     * @param attendantId is the id of the member that will be removed
     * @throws LateCancelException if trying to cancel when class is due in less than 2hrs
     */
    @Override
    public void removeAttendantFromClass(String gymClassId, String attendantId) throws LateCancelException {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        if (Duration.between(LocalDateTime.now(), gymClass.getScheduledTime()).toMinutes() < 120) {
            throw new LateCancelException("Sorry, the class is due in less than 2 hours!");
        }
        Member member = memberDao.getById(attendantId);
        gymClass.removeAttendant(member);
    }

    /**
     * Updates which instructor a class will have
     * @param gymClassId is the is of the gym class
     * @param newInstructorId is the id of the new instructor
     */
    @Override
    public void updateClassInstructor(String gymClassId, String newInstructorId) {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Instructor newInstructor = instructorDao.getInstructorById(newInstructorId);

        gymClass.setInstructor(newInstructor);

        gymClassDao.updateGymClass(gymClass);
    }
}
