package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;
import se.yrgo.domain.Member;
import se.yrgo.services.BookingManagementService;
import se.yrgo.services.GymClassManagementService;
import se.yrgo.services.InstructorManagementService;
import se.yrgo.services.MemberManagementService;
import se.yrgo.services.exceptions.AlreadyBookedToGymClassException;
import se.yrgo.services.exceptions.GymClassFullException;
import se.yrgo.services.exceptions.LateCancelException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
            System.out.println("\t" + "Enter your Member ID: ");
            System.out.println("\t" + "Enter empty to cancel");
            String choiceMember = scanner.nextLine();
            if(mm.findMemberById(choiceMember) != null){
                bookAndCancelClass(scanner, choiceMember);
            }
            else if(choiceMember.isEmpty()){
                return;
            }
            else {
                System.out.println("Member not found!");
            }
        }
    }
    private static void bookAndCancelClass(Scanner scanner, String memberId) {
        while(true){
            System.out.println("\t" + "0. Press '0' to return");
            System.out.println("\t" + "1. Press '1' if you want to book class");
            System.out.println("\t" + "2. Press '2' if you want to cancel class");
            String choiceMember2 = scanner.nextLine();
            switch (choiceMember2){
                case "0": {
                    return;
                }
                case "1": {
                    System.out.println("Search name of Class to book");
                    String gymClassName = scanner.nextLine();
                    Optional<GymClass> selectedClass = promptSelection(scanner, gm.getClassesByName(gymClassName), "class");
                    if (selectedClass.isEmpty()){
                        System.out.println("No class found");
                        break;
                    }
                    try {
                        bm.addAttendantToClass(selectedClass.get().getClassId(),memberId);
                    } catch (AlreadyBookedToGymClassException e) {
                        System.err.println("error at: " + e.getMessage());
                    } catch (GymClassFullException e){
                        System.err.println("Class is full");
                    }
                    break;
                }
                case "2": {
                    Optional<GymClass> selectedClass = promptSelection(scanner, mm.bookingCheck(memberId), "class");
                    if (selectedClass.isEmpty()){
                        System.out.println("No class found");
                        break;
                    }
                    try {
                        bm.removeAttendantFromClass(selectedClass.get().getClassId(),memberId);
                    } catch (LateCancelException e) {
                        System.err.println("error at: " + e.getMessage());
                    }
                }
                default:{
                    System.out.println("Invalid choice, please enter a number between 0 and 2");
                }
            }
        }
    }


    private static void sysadminOptions(Scanner scanner) {
        while (true) {
            System.out.println("What would you like to do?: ");
            System.out.println("\t" + "0. Press '0' to return to main menu");
            System.out.println("\t" + "1. Press '1' to see members, classes and instructors");
            System.out.println("\t" + "1. Press '2' add attendant to class");
            System.out.println("\t" + "2. Press '3' remove attendant from class");
            System.out.println("\t" + "3. Press '4' change instructor for class");
            System.out.println("Enter a number: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0": {
                    System.out.println("Returning to main menu");
                    return;
                }
                case "2": {
                    addAttendantToClass(scanner);
                    break;
                }
                case "3": {
                    removeAttendantFromClass(scanner);
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter a number between 0 and 2");
                }
            }
        }
    }

    private static void addAttendantToClass(Scanner scanner) {

        while (true) {
            System.out.println("Choose from the following options");
            System.out.println("\t" + "0. Press '0' to cancel");
            System.out.println("\t" + "1. Press '1' add an existing member to class");
            System.out.println("\t" + "2. Press '2' add a new member to class");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0": {
                    System.out.println("Returning to sysadmin options");
                    return;
                }
                case "1": {
                    Optional<Member> selectedMember = promptSelection(scanner, mm.getAllMembers(), "member");
                    if (selectedMember.isEmpty()) {
                        System.out.println("Cancelled.");
                        return;
                    }

                    Optional<GymClass> selectedClass = promptSelection(scanner, gm.getAllClasses(), "class");
                    if (selectedClass.isEmpty()) {
                        System.out.println("Cancelled.");
                        return;
                    }

                    try {
                        bm.addAttendantToClass(selectedClass.get().getClassId(), selectedMember.get().getMemberId());
                    } catch (GymClassFullException e) {
                        System.err.println(e.getMessage());
                    } catch (AlreadyBookedToGymClassException e) {
                        System.err.println("You are already booked to this class");
                    }

                    break;
                }
                case "2": {
                    System.out.println("Enter member ID: ");
                    String memberId = scanner.nextLine();

                    System.out.println("Enter member name: ");
                    String memberName = scanner.nextLine();

                    System.out.println("Enter member phone number: ");
                    String memberPhone = scanner.nextLine();

                    Member member = new Member(memberId, memberName, memberPhone);
                    mm.newMember(member);

                    Optional<GymClass> selectedClass = promptSelection(scanner, gm.getAllClasses(), "class");
                    if (selectedClass.isEmpty()) {
                        System.out.println("Cancelled.");
                        return;
                    }

                    try {
                        bm.addAttendantToClass(selectedClass.get().getClassId(), member.getMemberId());
                    } catch (GymClassFullException e) {
                        System.err.println(e.getMessage());
                    } catch (AlreadyBookedToGymClassException e) {
                        System.err.println("You are already booked to this class");
                    }
                }
            }
        }
    }

    private static void removeAttendantFromClass(Scanner scanner) {
        while (true) {

            Optional<Member> attendantToRemove = promptSelection(scanner, mm.getAllMembers(), "member");

            if (attendantToRemove.isEmpty()) {
                System.out.println("Cancelled.");
                return;
            }

            Optional<GymClass> selectedClass = promptSelection(scanner, mm.bookingCheck(attendantToRemove.get().getMemberId()), "class");

            if (selectedClass.isEmpty()) {
                System.out.println("Cancelled.");
                return;
            }

            bm.removeAttendantFromClass(selectedClass.get().getClassId(), attendantToRemove.get().getMemberId());
        }
    }

    private static <T> Optional<T> promptSelection(Scanner scanner, List<T> list, String itemType) {
        if (list.isEmpty()) {
            System.out.println("No " + itemType + "s available.");
            return Optional.empty();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("Select a " + itemType + " by number or 0 to cancel:");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) return Optional.empty();
                if (choice >= 1 && choice <= list.size()) {
                    return Optional.of(list.get(choice - 1));
                }
                System.out.println("Invalid number. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}