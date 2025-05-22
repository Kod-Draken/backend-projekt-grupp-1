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


/**
 * @author Alrik, Mattias, Najib
 * Does integration tests for the gym class bookings
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers.xml", "/datasource-test.xml" } )
public class InstructorServiceTest {

    @Autowired
    private InstructorManagementService is;

    @Test
    public void testNewInstructor(Instructor instructor) {

    }

    @Test
    public void testEditInstructor(Instructor changedInstructor) {

    }

    @Test
    public void testDeleteInstructor(Instructor deletedInstructor) {

    }

    @Test
    public void testFindInstructorById(String id) {

    }

    @Test
    public void testGetAllInstructors() {

    }

    @Test
    public void testGetInstructorByName(String name) {

    }

    @Test
    public void testGetGymClassesForInstructor(String id) {

    }

    @Test
    public void testGetGymClassForInstructor(Instructor instructor) {

    }
}