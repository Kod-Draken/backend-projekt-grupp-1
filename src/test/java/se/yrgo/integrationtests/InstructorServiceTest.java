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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Najib
 * Does integration tests for the gym class bookings
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers.xml", "/datasource-test.xml" } )
public class InstructorServiceTest {

    @Autowired
    private InstructorManagementService is;

    @Autowired
    private GymClassManagementService gcm;

    @BeforeEach
    public void setUp() {
        Instructor testInstructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
        is.newInstructor(testInstructor);

    }

    @Test
    public void testNewInstructor() {
        assertEquals(1, is.getAllInstructors().size()); // We expect only one instructor in the database
    }

    @Test
    public void testEditInstructor() {
        Instructor existing = is.findInstructorById("IN1");
        existing.setName("Other name");
        is.editInstructor(existing);

        assertEquals("Other name", is.findInstructorById("IN1").getName());
    }

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
        };
    }

    @Test
    public void testGetGymClassesForInstructor() {

    }

    @Test
    public void testGetGymClassForInstructor() {

    }
}