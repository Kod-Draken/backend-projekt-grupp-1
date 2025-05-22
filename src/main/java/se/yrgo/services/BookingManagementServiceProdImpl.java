package se.yrgo.services;

import se.yrgo.dataaccess.GymClassDao;
import se.yrgo.dataaccess.InstructorDao;
import se.yrgo.dataaccess.MemberDao;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;

public class BookingManagementServiceProdImpl implements BookingManagementService {
    private GymClassDao gymClassDao;
    private MemberDao memberDao;
    private InstructorDao instructorDao;

    public BookingManagementServiceProdImpl() {}

    public BookingManagementServiceProdImpl(GymClassDao gymClassDao, MemberDao memberDao, InstructorDao instructorDao) {
        this.gymClassDao = gymClassDao;
        this.memberDao = memberDao;
        this.instructorDao = instructorDao;
    }

    @Override
    public void addAttendantToClass(int gymClassId, String attendantId) throws GymClassFullException,
                                                                                AlreadyBookedToGymClassException,
                                                                                MemberIdNotFoundException {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Member newAttendant = memberDao.getById(attendantId);

        if (gymClass.isFull()) {
            throw new GymClassFullException("Sorry, class is fully booked!");
        }

        gymClass.addAttendant(newAttendant);

        System.out.println("Successfully added attendant " + newAttendant.toString());
    }

    @Override
    public void removeAttendantFromClass(int gymClassId, String attendantId) {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
    }

    @Override
    public void updateClassInstructor(int gymClassId, String instructorId) {
        GymClass gymClass = gymClassDao.getGymClassById(gymClassId);
        Instructor instructor = instructorDao.getInstructorById(instructorId);

        gymClass.setInstructor(instructor);

        gymClassDao.updateGymClass(gymClass);

    }
}
