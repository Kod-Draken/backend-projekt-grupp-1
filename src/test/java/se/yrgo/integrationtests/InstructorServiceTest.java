package se.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Instructor;
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

    @Test
    public void testNewInstructor() {
        Instructor test = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
        is.newInstructor(test);
        assertEquals(1, is.getAllInstructors().size());
    }

    @Test
    public void testEditInstructor() {

    }

    @Test
    public void testDeleteInstructor() {

    }

    @Test
    public void testFindInstructorById() {
    }

    @Test
    public void testGetAllInstructors() {

    }

    @Test
    public void testGetInstructorByName() {

    }

    @Test
    public void testGetGymClassesForInstructor() {

    }

    @Test
    public void testGetGymClassForInstructor() {

    }
}