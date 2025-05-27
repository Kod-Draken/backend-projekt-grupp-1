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
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Najib
 * Does integration tests for the gym class bookings
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers-test.xml", "/datasource-test.xml" } )
public class InstructorServiceTest {

    @Autowired
    private InstructorManagementService is;

    @Autowired
    private GymClassManagementService gcm;

    /**
     * Creates an instructor and saves it to the database
     */
    @BeforeEach
    public void setUp() {
        Instructor testInstructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
        is.newInstructor(testInstructor);
    }

    /**
     * This test checks if the list of instructors only is one (because only one has been added)
     * and that we have the correct instructor
     */
    @Test
    public void testNewInstructor() {
        assertEquals(1, is.getAllInstructors().size()); // We expect only one instructor in the database
        assertEquals("Bosse Bredsladd", is.getAllInstructors().getFirst().getName());
    }

    /**
     * Tests if an existing instructor changes the name and that the change was saved in the database
     */
    @Test
    public void testEditInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        existing.setName("Other name");
        is.editInstructor(existing);

        assertEquals("Other name", is.findInstructorById("IN1").getName());
    }

    /**
     * Tests if a deletion of an instructor is possible by comparing the size of the instructor-list
     * before and after deletion.
     */
    @Test
    public void testDeleteInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        assertEquals(1, is.getAllInstructors().size());
        is.deleteInstructor(existing);
        assertEquals(0, is.getAllInstructors().size());
    }

    /**
     * Tests if we can find an instructor and controls if it really matches the right one.
     */
    @Test
    public void testFindInstructorById() {
        Instructor existing = is.findInstructorById("IN1");
        assertEquals(existing, is.findInstructorById("IN1"));
    }

    /**
     * Test for exception throw when looking for non-existent instructor
     */
    @Test
    public void testFindNonExistentInstructors() {
        assertThrows(RuntimeException.class, () -> is.findInstructorById("IN2"));
    }

    /**
     * Test for getting all saved instructors
     */
    @Test
    public void testGetAllInstructors() {
        Instructor test2 = new Instructor("IN2", "Hasse Hamnsladd", "031-777444");
        Instructor test3 = new Instructor("IN3", "Lasse Långsladd", "031-777444");
        is.newInstructor(test2);
        is.newInstructor(test3);

        assertEquals(3, is.getAllInstructors().size());
        assertNotEquals(2, is.getAllInstructors().size());
    }

    /**
     * Test for finding instructor by name. Method returns a list of all matching names
     */
    @Test
    public void testGetInstructorByName() {
        for (Instructor i : is.getInstructorsByName("Bosse Bredsladd")) {
            assertEquals("Bosse Bredsladd", i.getName());
        }
    }

    /**
     * Test for retrieving classes for an instructor by checking number of classes (should only be 1)
     * and if it is the right class
     */
    @Test
    public void testGetGymClassesForInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", existing, LocalDateTime.now(), 10);
        gcm.addNewGymClass(testGymclass);

        assertEquals(1, is.getGymClassesForInstructor("IN1").size());
        assertEquals("GC1", is.getGymClassesForInstructor("IN1").getFirst().getClassId());
    }

    /**
     * Test for getting the right number of classes for an instructor by comparing before and after a class is added.
     */
    @Test
    public void testGetNumberOfClassesForInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", existing, LocalDateTime.now(), 10);

        assertEquals(0, is.getNumberOfClassesForInstructor(existing));
        gcm.addNewGymClass(testGymclass);
        long numberOfClasses = is.getNumberOfClassesForInstructor(existing);

        assertEquals(1, numberOfClasses);
    }
}