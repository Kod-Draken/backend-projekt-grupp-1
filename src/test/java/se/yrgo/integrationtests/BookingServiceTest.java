package se.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.GymClassFullException;

/**
 * @author Alrik, Mattias, Najib
 * Does integration tests for the gym class bookings
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers.xml", "/datasource-test.xml" } )
public class BookingServiceTest {

    @Autowired
    private BookingManagementService bms;

    @Test
    public void testAddAttendantToClass(int gymClassId, String attendantId) throws GymClassFullException {

    }

    @Test
    public void testRemoveAttendantFromClass(int gymClassId, String attendantId) {

    }

    @Test
    public void testUpdateClassInstructor(int gymClassId, String instructorId) {

    }

}
