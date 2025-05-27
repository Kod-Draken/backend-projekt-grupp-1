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
 *
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
     * Sets up the data with some instructors, members and gym classes to interact with
     */
    private static void setUpData() {
        Instructor instructor = new Instructor("IN1", "Bosse Bredsladd", "031-777444");
        Instructor instructor2 = new Instructor("IN2", "Lasse Långsladd", "031-999222");
        Instructor instructor3 = new Instructor("IN3", "Eva Johansson", "0754-222333");

        GymClass gymClass = new GymClass("BX0001", "Boxning", "Lätt boxningspass fokus på hög puls och förbättrad koordination", "Sal 3", instructor, LocalDateTime.now(), 20);
        instructor.addGymClassToInstructorSchedule(gymClass);
        GymClass gymClass2 = new GymClass("PI0001", "Pilates", "Lugnt och fint", "Sal 1", instructor, LocalDateTime.now(), 20);
        instructor.addGymClassToInstructorSchedule(gymClass2);

        is.newInstructor(instructor);
        gm.addNewGymClass(gymClass);
        gm.addNewGymClass(gymClass2);

        GymClass gymClass3 = new GymClass("YO0001", "Yoga", "Vinyasa Flow", "Hot-salen", instructor2, LocalDateTime.now(), 20);
        instructor2.addGymClassToInstructorSchedule(gymClass3);

        is.newInstructor(instructor2);
        gm.addNewGymClass(gymClass3);

        GymClass gymClass4 = new GymClass("SP0001", "Spinning", "30 min", "Spinning-salen", instructor3, LocalDateTime.now().plusDays(+5), 20);
        instructor3.addGymClassToInstructorSchedule(gymClass4);
        GymClass gymClass5 = new GymClass("BP0001", "Body Pump", "60 min", "Sal 10", instructor3, LocalDateTime.now(), 20);
        instructor3.addGymClassToInstructorSchedule(gymClass5);

        is.newInstructor(instructor3);
        gm.addNewGymClass(gymClass4);
        gm.addNewGymClass(gymClass5);

        mm.newMember(new Member("S001", "Janne Björnsson", "0758293153"));
        mm.newMember(new Member("S002", "Doris Jönsson", "0744356987"));
        mm.newMember(new Member("S003", "Allan Borg", "073863987"));
    }

    /**
     * Base layer of the I/O, 2 roles, member and SysAdmin, different layers of interactivity.
     */
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
                        break;
                    }
                }
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Operations that can be conducted by a member of the gym. Requires a member ID to access the next layer.
     *
     * @param scanner used for input from the user.
     */
    private static void memberOptions(Scanner scanner) {
        while (true) {
            String choiceMember = "";
            try {
                System.out.println("\t" + "Enter your Member ID: ");
                System.out.println("\t" + "Enter empty to cancel");
                choiceMember = scanner.nextLine();
                if (mm.findMemberById(choiceMember) != null) {
                    bookAndCancelClass(scanner, choiceMember);
                }
            } catch (RuntimeException e) {
                if(!choiceMember.isEmpty()) {
                    System.err.println("Member not found!");
                }
            }
            if (choiceMember.isEmpty()) {
                return;
            }
        }
    }

    /**
     * A member can book themselves to a class or cancel the class
     *
     * @param scanner is for user input
     * @param memberId required to use the method, user enters their id in the previous method
     */
    private static void bookAndCancelClass(Scanner scanner, String memberId) {
        while (true) {
            System.out.println("\t" + "0. Press '0' to return");
            System.out.println("\t" + "1. Press '1' if you want to book class");
            System.out.println("\t" + "2. Press '2' if you want to cancel class");
            String choiceMember2 = scanner.nextLine();
            switch (choiceMember2) {
                case "0": {
                    return;
                }
                case "1": {
                    System.out.println("Search name of Class to book");
                    String gymClassName = scanner.nextLine();
                    Optional<GymClass> selectedClass = promptSelection(scanner, gm.getClassesByName(gymClassName.substring(0,1).toUpperCase() + gymClassName.substring(1)), "class");
                    if (selectedClass.isEmpty()) {
                        System.out.println("No class found");
                        break;
                    }
                    try {
                        bm.addAttendantToClass(selectedClass.get().getClassId(), memberId);
                    } catch (AlreadyBookedToGymClassException e) {
                        System.err.println("error at: " + e.getMessage());
                    } catch (GymClassFullException e) {
                        System.err.println("Class is full");
                    }
                    break;
                }
                case "2": {
                    Optional<GymClass> selectedClass = promptSelection(scanner, bm.bookingCheck(memberId), "class");
                    if (selectedClass.isEmpty()) {
                        break;
                    }
                    try {
                        bm.removeAttendantFromClass(selectedClass.get().getClassId(), memberId);
                    } catch (LateCancelException e) {
                        System.err.println("error at: " + e.getMessage());
                    }
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter a number between 0 and 2");
                    break;
                }
            }
        }
    }

    /**
     * Options for a System Admin, they have a lot more tools at their disposal such as adding, removing, editing classes, new members, new instructors.
     *
     * @param scanner used for user input.
     */
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
                case "1": {
                    readDataOptions(scanner);
                    break;
                }

                case "2": {
                    addAttendantToClass(scanner);
                    break;
                }
                case "3": {
                    removeAttendantFromClass(scanner);
                    break;
                }
                case "4": {
                    changeInstructorForClass(scanner);
                    break;
                }
                default: {
                    System.out.println("Invalid choice, please enter a number between 0 and 2");
                }
            }
        }
    }

    /**
     * Select options for READING data from the database
     *
     * @param scanner reads input text from user
     */
    private static void readDataOptions(Scanner scanner) {
        while (true) {
            System.out.println("Choose from the following options");
            System.out.println("\t" + "0. Press '0' to cancel");
            System.out.println("\t" + "1. Press '1' see all members");
            System.out.println("\t" + "2. Press '2' see all instructors");
            System.out.println("\t" + "3. Press '3' see all classes");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0": {
                    System.out.println("Returning to sysadmin options");
                    return;
                }
                case "1": {
                    for (Member member : mm.getAllMembers()) {
                        System.out.println(member);
                    }
                    System.out.println();
                    break;
                }
                case "2": {
                    for (Instructor instructor : is.getAllInstructors()) {
                        System.out.println(instructor);
                    }
                    System.out.println();
                    break;
                }
                case "3": {
                    for (GymClass gymClass : gm.getAllClasses()) {
                        System.out.println(gymClass);
                    }
                    System.out.println();
                    break;
                }
            }
        }
    }

    /**
     * Adds a member to a gymClass's attendant list, vice versa.
     * Can do it both with an existing member, or register a new member.
     *
     * @param scanner used for user input.
     */
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
                    } catch (GymClassFullException | AlreadyBookedToGymClassException e) {
                        System.err.println(e.getMessage());
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
                    break;
                }
            }
        }
    }

    /**
     * Removes a member from a gymClass's attendants list.
     *
     * @param scanner used for user input.
     */
    private static void removeAttendantFromClass(Scanner scanner) {
        while (true) {

            Optional<Member> attendantToRemove = promptSelection(scanner, mm.getAllMembers(), "member");

            if (attendantToRemove.isEmpty()) {
                System.out.println("Cancelled.");
                return;
            }

            Optional<GymClass> selectedClass = promptSelection(scanner, bm.bookingCheck(attendantToRemove.get().getMemberId()), "class");

            if (selectedClass.isEmpty()) {
                System.out.println("Cancelled.");
                return;
            }

            bm.removeAttendantFromClass(selectedClass.get().getClassId(), attendantToRemove.get().getMemberId());
        }
    }

    /**
     * Changes the instructor for a class by selecting instructor, from which class and a replacer
     * @param scanner is for user input
     */
    private static void changeInstructorForClass(Scanner scanner) {
        System.out.println("Which instructor would like to change?");
        Optional<Instructor> selectedInstructor = promptSelection(scanner, is.getAllInstructors(), "instructor");
        if (selectedInstructor.isEmpty()) {
            System.out.println("Cancelled.");
            return;
        }

        System.out.println("From which of the classes?");
        Optional<GymClass> selectedGymClass = promptSelection(scanner, is.getGymClassesForInstructor(selectedInstructor.get().getInstructorId()), "gym class");
        if (selectedGymClass.isEmpty()) {
            System.out.println("Cancelled.");
            return;
        }

        System.out.println("To which other instructor would like to change?");
        Optional<Instructor> newSelectedInstructor = promptSelection(scanner, is.getAllInstructors(), "instructor");
        if (newSelectedInstructor.isEmpty()) {
            System.out.println("Cancelled.");
            return;
        }

        bm.updateClassInstructor(selectedGymClass.get().getClassId(), newSelectedInstructor.get().getInstructorId());
        System.out.println("Gym class " + selectedGymClass.get().getName() + " now has instructor " + newSelectedInstructor.get().getName() + "\n");
    }

    /**
     * Helper method used to print out contents of a collection and register a selection from the user.
     *
     * @param scanner  used for user input.
     * @param list     takes a list of anything and puts the elements in an ordered list in STDOUT.
     * @param itemType clue to what kind of items the list contains
     * @param <T>      An object
     * @return one of two things, a list containing only the selected object OR null
     */
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