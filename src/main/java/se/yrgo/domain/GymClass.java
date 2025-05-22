package se.yrgo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mattias
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

    @ManyToMany(mappedBy = "bookedClasses")
    private Set<Member> members = new HashSet<>();


    public GymClass() {}

    public GymClass(String name, String description, String roomName, LocalDateTime scheduledAt) {
        this.name = name;
        this.description = description;
        this.roomName = roomName;
        this.scheduledAt = scheduledAt;
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

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }


    public Set<Member> getClassesToInstruct() {
        return members;
    }
}
