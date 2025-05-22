package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Instructor;
import se.yrgo.services.InstructorManagementService;

/**
 * This class is the Client in an application where you can book and manage gym classes
 * @author Alrik, Mattias, Najib
 */
public class Client {
    private static ClassPathXmlApplicationContext container;

    public static void main(String[] args) {
        setUpData();
        container = new ClassPathXmlApplicationContext("application.xml");




        container.close();
    }

    /**
     * Sets up the data with some instructors and gym classes to interact with
     */
    private static void setUpData() {
        container = new ClassPathXmlApplicationContext("application.xml");
        InstructorManagementService is = container.getBean(InstructorManagementService.class);

        is.newInstructor(new Instructor("IN1", "Bosse Bredsladd", "031-777444"));

        container.close();
    }
}