package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;

import java.time.LocalDateTime;

/**
 * This class is the Client in an application where you can book and manage gym classes
 * @author Alrik, Mattias, Najib
 */
public class Client {
    private static ClassPathXmlApplicationContext container;

    public static void main(String[] args) {
        setUpData();
        container = new ClassPathXmlApplicationContext("production-application.xml");




        container.close();
    }

    /**
     * Sets up the data with some instructors and gym classes to interact with
     */
    private static void setUpData() {
        container = new ClassPathXmlApplicationContext("production-application.xml");
        InstructorManagementService is = container.getBean(InstructorManagementService.class);
        GymClassManagementService gm = container.getBean(GymClassManagementService.class);
        MemberManagementService mm = container.getBean(MemberManagementService.class);

        is.newInstructor(new Instructor("IN1", "Bosse Bredsladd", "031-777444"));
        gm.addNewGymClass(new GymClass("Boxning", "Lätt boxningspass fokus på hög puls och förbättrad koordination", "Sal 3", LocalDateTime.now(), 20));
        mm.newMember(new Member("S001","Janne Björnsson","0758293153"));


        container.close();
    }
}