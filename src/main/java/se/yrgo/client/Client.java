package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * This class is the Client in an application where you can book and manage gym classes
 * @author Alrik, Mattias, Najib
 */
public class Client {
    private static final ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("production-application.xml");
    private static final InstructorManagementService is = container.getBean(InstructorManagementService.class);
    private static final GymClassManagementService gm = container.getBean(GymClassManagementService.class);
    private static final MemberManagementService mm = container.getBean(MemberManagementService.class);
    private static final BookingManagementService bm = container.getBean(BookingManagementService.class);

    public static void main(String[] args) {
        setUpData();
        menu();

        container.close();
    }

    /**
     * Sets up the data with some instructors and gym classes to interact with
     */
    private static void setUpData() {
        Instructor instructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");

        is.newInstructor(instructor);
        gm.addNewGymClass(new GymClass("BX0001","Boxning", "Lätt boxningspass fokus på hög puls och förbättrad koordination", "Sal 3", instructor, LocalDateTime.now(), 20));
        mm.newMember(new Member("S001","Janne Björnsson","0758293153"));
    }

    private static void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose from the following options: ");
                System.out.println("\t" + "0. Press '0' to quit");
                System.out.println("\t" + "1. Press '1' if you are a member");
                System.out.println("\t" + "2. Press '2' if you are a sysadmin");
                System.out.println("Enter a number: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "0": {
                        System.out.println("Shutting down...");
                        return;
                    }
                    case "1": {
                        System.out.println("You are a member");
                        memberOptions(scanner);
                        break;
                    }
                    case "2": {
                        System.out.println("You are a sysadmin");
                        sysadminOptions(scanner);
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice, please enter a number between 0 and 2");
                    }
                }
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void memberOptions(Scanner scanner) {
        while(true){
            try (Scanner scan = new Scanner(System.in)) {

            }
        }
    }

    private static void sysadminOptions(Scanner scanner) {
        while (true) {
            System.out.println("What would you like to do?: ");
            System.out.println("\t" + "0. Press '0' to return to main menu");
            System.out.println("\t" + "1. Press '1' add attendant to class");
            System.out.println("\t" + "2. Press '2' remove attendant from class");
            System.out.println("\t" + "3. Press '3' change instructor for class");
            System.out.println("Enter a number: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0": {
                    System.out.println("Returning to main menu");
                    return;
                }
                case "1": {
                    addAttendantToClass();
                }
            }
        }
    }

    private static void addAttendantToClass(Scanner scanner) {
        while (true) {}
    }
}