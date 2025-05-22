package se.yrgo.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Najib Bardash
 *
 * This class represents an instructor working at a gym
 */
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20, unique = true, nullable = false)
    private String instructorId;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String phone;

    private int numberOfClasses;

    @OneToMany (mappedBy = "instructor")
    private Set<GymClass> gymClasses;

    public Instructor() {}

    public Instructor(String instructorId, String name, String phone) {
        this.instructorId = instructorId;
        this.name = name;
        this.phone = phone;
        this.gymClasses = new HashSet<>();
    }

    /**
     *
     * @return the id of the instructor
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     *
     * @return the name of the instructor
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the phone number of the instructor
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return the number of classes the instructor has
     */
    public int getNumberOfClasses() {
        return this.gymClasses.size();
    }

    /**
     * Adds gym classes to the instructors schedule
     * @param gymclass is the class that will be added
     */
    public void addGymClassToInstructorSchedule(GymClass gymclass) {
        this.gymClasses.add(gymclass);
    }

    /**
     *
     * @return the classes for the instructor
     */
    public Set<GymClass> getInstructorGymClasses() {
        return Collections.unmodifiableSet(this.gymClasses);
    }
}
