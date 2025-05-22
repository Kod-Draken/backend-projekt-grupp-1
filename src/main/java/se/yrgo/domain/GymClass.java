package se.yrgo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mattias
 * Represents a class at a gym, a certain timestamp it happens, attendants, an instructor, etc.
 */
@Entity
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    private int capacity;

    @ManyToMany(mappedBy = "bookedClasses")
    private Set<Member> attendants = new HashSet<>();


    public GymClass() {}

    public GymClass(String name, String description, String roomName, LocalDateTime scheduledAt, int capacity) {
        this.name = name;
        this.description = description;
        this.roomName = roomName;
        this.scheduledAt = scheduledAt;
        this.capacity = capacity;
    }

    public String toString() {
        return "GymClass \n Name = "
                + name
                + "\nDescription = "
                + description
                + "\nInstructor = "
                + instructor.getName()
                + "\nScheduled At = "
                + scheduledAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRoomName() {
        return roomName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledAt;
    }

    public Set<Member> getAttendants() {
        return attendants;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addAttendant(Member attendant) {
        attendants.add(attendant);
    }

    public void removeAttendant(Member attendant) {
        attendants.remove(attendant);
    }

    public Boolean isFull(){
        return attendants.size() >= capacity;
    }

}
