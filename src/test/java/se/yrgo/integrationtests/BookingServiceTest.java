package se.yrgo.integrationtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;
import se.yrgo.services.exceptions.LateCancelException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alrik, Mattias, Najib
 * Does integration tests for the gym class bookings
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers-test.xml", "/datasource-test.xml" } )
public class BookingServiceTest {

    @Autowired
    private BookingManagementService bms;

    @Autowired
    private InstructorManagementService ims;

    @Autowired
    private GymClassManagementService gcm;

    @Autowired
    private MemberManagementService mms;

    private final Instructor testInstructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
    private final GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", testInstructor, LocalDateTime.now().plusDays(10), 10);
    private final Member testMember = new Member("ME1", "Lars Andersson", "0771-444333");

    /**
     * Method that sets up some initial test data
     */
    @BeforeEach
    public void setUp() {
        ims.newInstructor(testInstructor);
        gcm.addNewGymClass(testGymclass);
        mms.newMember(testMember);
    }

    /**
     * This test tries to add an attendant to a class and checks if the attendants have increased before and after adding
     * @throws GymClassFullException if the class is full
     * @throws AlreadyBookedToGymClassException if a double booking is attempted
     */
    @Test
    public void testAddAttendantToClass() throws GymClassFullException, AlreadyBookedToGymClassException  {
        assertEquals(0, gcm.getClassById("GC1").getAttendants().size());
        bms.addAttendantToClass("GC1","ME1");

        assertEquals(1, gcm.getClassById("GC1").getAttendants().size()); // The list of attendants increased from 0 to 1
    }

    /**
     * Test that should throw an exception if a double booking is attempted
     * @throws GymClassFullException if the class is full
     * @throws AlreadyBookedToGymClassException if a double booking is attempted
     */
    @Test
    public void testAddDuplicateAttendantToClass() throws GymClassFullException, AlreadyBookedToGymClassException{
        bms.addAttendantToClass("GC1","ME1");
        assertThrows(AlreadyBookedToGymClassException.class, () -> bms.addAttendantToClass("GC1","ME1"));
    }

    /**
     * Test that should throw an exception if a class is full if the capacity of the class is reached
     * @throws GymClassFullException if the class is full
     * @throws AlreadyBookedToGymClassException if a double booking is attempted
     */
    @Test void testThrowsWhenClassIsFull() throws GymClassFullException, AlreadyBookedToGymClassException {
        GymClass testGymClass2 = new GymClass("GC2", "Boxning", "30 min", "Rum 14", testInstructor, LocalDateTime.now().plusDays(10), 1);
        gcm.addNewGymClass(testGymClass2);
        bms.addAttendantToClass("GC2", "ME1");
        Member newMember = new Member("ME2", "Berit Andersson", "123214");
        mms.newMember(newMember);

        assertThrows(GymClassFullException.class, () -> bms.addAttendantToClass("GC2", "ME2"));
    }

    /**
     * Test for removing an attendant by checking the size of the attendants before and after removal
     * @throws GymClassFullException if class is full
     * @throws AlreadyBookedToGymClassException if a double booking is attempted
     * @throws LateCancelException if a late cancellation occurs
     */
    @Test
    public void testRemoveAttendantFromClass() throws GymClassFullException, AlreadyBookedToGymClassException, LateCancelException {
        bms.addAttendantToClass("GC1","ME1");
        assertEquals(1, gcm.getMembersByGymClass("GC1").size());

        bms.removeAttendantFromClass("GC1","ME1");
        assertEquals(0, gcm.getMembersByGymClass("GC1").size());
    }

    /**
     * Test for changing instructor by comparing the new instructors name to the correct one
     */
    @Test
    public void testUpdateClassInstructor() {
        Instructor newInstructor = new Instructor("IN2", "Anna Svantesson", "08-3242346");
        ims.newInstructor(newInstructor);

        testGymclass.setInstructor(newInstructor);
        bms.updateClassInstructor("GC1","IN2");

        assertEquals("Anna Svantesson", gcm.getClassById("GC1").getInstructor().getName());
    }

    /**
     * Test if a newly booked class is in the list of booked classes for a member
     */
    @Test
    public void testGetBookedClasses(){
        testMember.getAllBookedClasses().add(testGymclass);
        mms.editMember(testMember);
        assertEquals(testGymclass, bms.bookingCheck(testMember.getMemberId()).getFirst());
    }
}
