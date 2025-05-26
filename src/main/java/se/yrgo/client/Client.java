package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * This class is the Client in an application where you can book and manage gym classes
 * @author Alrik, Mattias, Najib
 */
public class Client {
    private static final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("production-application.xml");

    public static void main(String[] args) {
        setUpData();

        InstructorManagementService is = container.getBean(InstructorManagementService.class);
        GymClassManagementService gm = container.getBean(GymClassManagementService.class);
        MemberManagementService mm = container.getBean(MemberManagementService.class);
        BookingManagementService bm = container.getBean(BookingManagementService.class);

        System.out.println(is.getAllInstructors());
        System.out.println(gm.getAllClasses());
        System.out.println(mm.getAllMembers());





        container.close();
    }

    private static void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a number");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("choice made: " + choice);
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Sets up the data with some instructors and gym classes to interact with
     */
    private static void setUpData() {
        InstructorManagementService is = container.getBean(InstructorManagementService.class);
        GymClassManagementService gm = container.getBean(GymClassManagementService.class);
        MemberManagementService mm = container.getBean(MemberManagementService.class);

        Instructor instructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");

        is.newInstructor(instructor);
        gm.addNewGymClass(new GymClass("BX0001","Boxning", "Lätt boxningspass fokus på hög puls och förbättrad koordination", "Sal 3", instructor, LocalDateTime.now(), 20));
        mm.newMember(new Member("S001","Janne Björnsson","0758293153"));
    }
}