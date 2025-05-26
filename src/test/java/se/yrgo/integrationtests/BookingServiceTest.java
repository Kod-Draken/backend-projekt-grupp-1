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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

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

    private Instructor testInstructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
    private GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", testInstructor, LocalDateTime.now().plusDays(10), 10);
    private Member testMember = new Member("ME1", "Lars Andersson", "0771-444333");

    @BeforeEach
    public void setUp() {
        ims.newInstructor(testInstructor);
        gcm.addNewGymClass(testGymclass);
        mms.newMember(testMember);
    }

    @Test
    public void testAddAttendantToClass() throws GymClassFullException, AlreadyBookedToGymClassException {
        bms.addAttendantToClass("GC1","ME1");
        StringBuilder str = new StringBuilder();
        for (Member mem : testGymclass.getAttendants()) {
            str.append(mem.getName()).append(", ");
        }
        assertEquals("Lars Andersson, ", str.toString());
    }

    @Test void testThrowsWhenClassIsFull() throws GymClassFullException {

    }

    @Test
    public void testRemoveAttendantFromClass() throws GymClassFullException, AlreadyBookedToGymClassException, LateCancelException {
        bms.addAttendantToClass("GC1","ME1");
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
}
