package se.yrgo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    private LocalDateTime scheduledAt;

    @ManyToMany(mappedBy = "bookedClasses")
    private Set<Member> subjectsToTeach = new HashSet<>();


    public GymClass() {}

    public GymClass(String name, String description, String roomName, LocalDateTime scheduledAt) {}

    public String toString() {
        return "GymClass [name="
                + name
                + ", description="
                + description
                + ", instructor= PLACEHOLDER"
                + ", scheduledAt="
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

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Set<Member> getSubjectsToTeach() {
        return subjectsToTeach;
    }
}
