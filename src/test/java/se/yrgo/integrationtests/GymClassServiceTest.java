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
    private static GymClass gymClass;

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

    @Test
    void testEditGymClass() {
        gymClass.setName("Another name");

        gsTest.editGymClass(gymClass);

        assertEquals("Another name", gsTest.getClassById("apa100").getName());

    }

    @Test
    void testDeleteGymClass() {

    }

    @Test
    void testGetAllGymClasses() {


        assertEquals(1, gsTest.getAllClasses().size());
    }


}