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

import static org.junit.jupiter.api.Assertions.assertEquals;


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
     *
     */
    @Test
    public void testDeleteInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        is.deleteInstructor(existing);
        assertEquals(0, is.getAllInstructors().size());
    }

    @Test
    public void testFindInstructorById() {
        Instructor existing = is.findInstructorById("IN1");
        assertEquals(existing, is.findInstructorById("IN1"));
    }

    @Test
    public void testGetAllInstructors() {
        Instructor test2 = new Instructor("IN2", "Hasse Hamnsladd", "031-777444");
        Instructor test3 = new Instructor("IN3", "Lasse Långsladd", "031-777444");
        is.newInstructor(test2);
        is.newInstructor(test3);

        assertEquals(3, is.getAllInstructors().size());
    }

    @Test
    public void testGetInstructorByName() {
        for (Instructor i : is.getInstructorsByName("Bosse Bredsladd")) {
            assertEquals("Bosse Bredsladd", i.getName());
        }
    }

    @Test
    public void testGetGymClassesForInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", existing, LocalDateTime.now(), 10);
        gcm.addNewGymClass(testGymclass);

        assertEquals(1, is.getGymClassesForInstructor("IN1").size());
    }

    @Test
    public void testGetNumberOfClassesForInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        GymClass testGymclass = new GymClass("GC1", "Yoga", "Vinyasa Flow", "Rum 14", existing, LocalDateTime.now(), 10);
        gcm.addNewGymClass(testGymclass);
        long numberOfClasses = is.getNumberOfClassesForInstructor(existing);

        assertEquals(1, numberOfClasses);
    }
}