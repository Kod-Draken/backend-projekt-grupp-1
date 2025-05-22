package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Instructor;
import se.yrgo.services.InstructorManagementService;

public class Client {
    private static ClassPathXmlApplicationContext container;

    public static void main(String[] args) {
        setUpData();
        container = new ClassPathXmlApplicationContext("application.xml");




        container.close();
    }

    private static void setUpData() {
        container = new ClassPathXmlApplicationContext("application.xml");
        InstructorManagementService is = container.getBean(InstructorManagementService.class);

        is.newInstructor(new Instructor("IN1", "Bosse Bredsladd", "031-777444"));

        container.close();
    }
}