package se.yrgo.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20)
    private String instructorId;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String phone;

    private int numberOfClasses;

    @OneToMany
    @JoinColumn(name = "INSTRUCTOR_FK")
    private Set<GymClass> gymClasses;

    public Instructor() {}

    public Instructor(String instructorId, String name, String phone) {
        this.instructorId = instructorId;
        this.name = name;
        this.phone = phone;
        this.gymClasses = new HashSet<>();
    }

    public void addGymClassToInstructorSchedule(GymClass gymclass) {
        this.gymClasses.add(gymclass);
    }

    public Set<GymClass> getInstructorGymClasses() {
        return Collections.unmodifiableSet(this.gymClasses);
    }

}
