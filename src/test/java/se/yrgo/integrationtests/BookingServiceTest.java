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
import java.util.Set;

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

    @BeforeEach
    public void setUp() {
        ims.newInstructor(testInstructor);
        gcm.addNewGymClass(testGymclass);
        mms.newMember(testMember);
    }

    @Test
    public void testAddAttendantToClass() throws GymClassFullException, AlreadyBookedToGymClassException  {
        assertEquals(0, gcm.getClassById("GC1").getAttendants().size());
        bms.addAttendantToClass("GC1","ME1");

        assertEquals(1, gcm.getClassById("GC1").getAttendants().size()); // The list of attendants increased from 0 to 1
    }

    @Test
    public void testAddDuplicateAttendantToClass() throws GymClassFullException, AlreadyBookedToGymClassException{
        bms.addAttendantToClass("GC1","ME1");
        assertThrows(AlreadyBookedToGymClassException.class, () -> bms.addAttendantToClass("GC1","ME1"));

    }

    @Test void testThrowsWhenClassIsFull() throws GymClassFullException, AlreadyBookedToGymClassException {
        GymClass testGymClass2 = new GymClass("GC2", "Boxning", "30 min", "Rum 14", testInstructor, LocalDateTime.now().plusDays(10), 1);
        gcm.addNewGymClass(testGymClass2);
        bms.addAttendantToClass("GC2", "ME1");
        Member newMember = new Member("ME2", "Berit Andersson", "123214");
        mms.newMember(newMember);

        assertThrows(GymClassFullException.class, () -> bms.addAttendantToClass("GC2", "ME2"));
    }

    @Test
    public void testRemoveAttendantFromClass() throws GymClassFullException, AlreadyBookedToGymClassException, LateCancelException {
        GymClass newGymClass = new GymClass("GC2", "Spinning", "30 min", "Rum 10", testInstructor, LocalDateTime.now().plusDays(10), 10);
        gcm.addNewGymClass(newGymClass);
        bms.addAttendantToClass("GC2","ME1");

        bms.removeAttendantFromClass("GC1","ME1");
        assertNotEquals(1, testGymclass.getAttendants().size());
    }

    @Test
    public void testUpdateClassInstructor() {
        Instructor newInstructor = new Instructor("IN2", "Anna Svantesson", "08-3242346");
        ims.newInstructor(newInstructor);

        testGymclass.setInstructor(newInstructor);
        bms.updateClassInstructor("GC1","IN2");

        assertEquals("Anna Svantesson", gcm.getClassById("GC1").getInstructor().getName());
    }

    @Test
    public void testGetBookedClasses(){
        testMember.getAllBookedClasses().add(testGymclass);
        mms.editMember(testMember);
        assertEquals(testGymclass, bms.bookingCheck(testMember.getMemberId()).getFirst());
    }
}
