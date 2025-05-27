package se.yrgo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mattias
 * Represents a class at a gym, a certain timestamp it happens, attendants, an instructor, etc.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (length = 20, unique = true, nullable = false)
    private String classId;

    @Column (length = 20)
    private String name;

    @Column (length = 20)
    private String description;

    @Column (length = 20)
    private String roomName;

    @ManyToOne()
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    private LocalDateTime scheduledAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");

    private int capacity;

    @ManyToMany(mappedBy = "bookedClasses")
    private final Set<Member> attendants = new HashSet<>();


    public GymClass() {}

    public GymClass(String classId, String name, String description, String roomName, Instructor instructor, LocalDateTime scheduledAt, int capacity) {
        this.classId = classId;
        this.name = name;
        this.description = description;
        this.roomName = roomName;
        this.instructor = instructor;
        this.scheduledAt = scheduledAt;
        this.capacity = capacity;
    }

    /**
     * A simple yet good-looking Stringification of the object
     * @return
     */
    @Override
    public String toString() {
        return String.format(
                "Name: %s\tDescription: %s\tInstructor: %s\tScheduled At: %s",
                name,
                description,
                instructor.getName(),
                scheduledAt.format(formatter)
        );
    }

    /**
     * @return the database primary key
     */
    public int getId() {
        return id;
    }

    /**
     * @return the unique identifier of the entity, not PK!
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return a short string describing the class
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a new description
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the room where the class takes place
     */
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledAt;
    }
    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Set<Member> getAttendants() {
        return attendants;
    }

    /**
     * @return the total capacity, the amount of people who can be booked to the class
     */
    public int getCapacity() {
        return capacity;
    }

    public void addAttendant(Member attendant) {
        attendants.add(attendant);
    }

    public void removeAttendant(Member attendant) {
        attendants.remove(attendant);
    }

    /**
     * Checks if a class's total attendants is equal to its capacity
     * @return true if so is the case, otherwise false.
     */
    public Boolean isFull(){
        return attendants.size() >= capacity;
    }
}
