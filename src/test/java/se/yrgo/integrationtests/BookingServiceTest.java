package se.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.exceptions.GymClassFullException;

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

    @Test
    public void testAddAttendantToClass() throws GymClassFullException {

    }

    @Test
    public void testRemoveAttendantFromClass() {

    }

    @Test
    public void testUpdateClassInstructor() {

    }

}
