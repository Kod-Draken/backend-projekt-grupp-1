package se.yrgo.integrationtests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mattias
 */

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers-test.xml", "/datasource-test.xml"} )
public class GymClassServiceTest {

    @Autowired
    private GymClassManagementService gsTest;
    @Autowired
    private InstructorManagementService isTest;
    @Autowired
    private MemberManagementService msTest;
    private static GymClass gymClass;

    /**
     * Sets up data to use in the tests
     */
    @BeforeEach
    void setUp() {
        Instructor instructor = new Instructor("ERIAND76", "Erik Anderzon", "0748951122");

        // Persist the instructor to database
        isTest.newInstructor(instructor);

        gymClass = new GymClass("apa100", "Apträning", "Klättra som apor i ribstolar", "sal 54", instructor, LocalDateTime.now(), 10);

        gsTest.addNewGymClass(gymClass);
    }

    @Test
    void testAddNewGymClass() {
        assertEquals(1, gsTest.getAllClasses().size());
    }

    @Test
    void testGetClassesByName() {
        assertEquals(1, gsTest.getClassesByName("Apträning").size());
    }

    @Test
    void testGetGymClassById() {
        assertEquals(gymClass, gsTest.getClassById("apa100"));
    }

    /**
     * Sets a new name for testClass and saves to DB, then checks if the name was updated in the DB
     * @throws AlreadyBookedToGymClassException
     */
    @Test
    void testEditGymClass() throws AlreadyBookedToGymClassException {
        gymClass.setName("Another name");

        gsTest.editGymClass(gymClass);

        assertEquals("Another name", gsTest.getClassById("apa100").getName());
    }

    /**
     * The DB contains 1 class so we expect 1 as result
     */
    @Test
    void testGetAllGymClasses() {
        assertEquals(1, gsTest.getAllClasses().size());
    }

    /**
     * Delete the test class and make sure that the database has 0 classes
     */
    @Test
    void testDeleteGymClass() {
        gsTest.deleteGymClass(gymClass);
        assertEquals(0, gsTest.getAllClasses().size());
    }

    /**
     * Tests if a member was added to the class by testing the size of the members and if the id's match
     * @throws AlreadyBookedToGymClassException if a double booking is attempted
     */
    @Test
    void testGetMembersByGymClass() throws AlreadyBookedToGymClassException {
        Member newMember = new Member("M01", "Allan Vall", "031-742356");
        gymClass.addAttendant(newMember);
        newMember.addBookedClass(gymClass);
        msTest.newMember(newMember);
        gsTest.editGymClass(gymClass);

        assertEquals(1, gsTest.getMembersByGymClass("apa100").size());
        assertEquals("M01", gsTest.getMembersByGymClass("apa100").getFirst().getMemberId());
    }
}