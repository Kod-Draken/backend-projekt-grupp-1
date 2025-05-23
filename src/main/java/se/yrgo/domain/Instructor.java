package se.yrgo.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Najib Bardash
 *
 * This class represents an instructor working at a gym
 */
@SuppressWarnings("unused")
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
     * @return the instructors primary key (int)
     */
    public int getId() {
        return id;
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
     * @param gymClass is the class that will be added
     */
    public void addGymClassToInstructorSchedule(GymClass gymClass) {
        this.gymClasses.add(gymClass);
    }

    /**
     *
     * @return the classes for the instructor
     */
    public Set<GymClass> getInstructorGymClasses() {
        return Collections.unmodifiableSet(this.gymClasses);
    }

    /**
     *
     * @param name is the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param phone is the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id == that.id && numberOfClasses == that.numberOfClasses && Objects.equals(instructorId, that.instructorId) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(gymClasses, that.gymClasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instructorId, name, phone, numberOfClasses, gymClasses);
    }

    @Override
    public String toString() {
        return "Instructor: " +
                "instructorId='" + instructorId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfClasses=" + numberOfClasses;
    }
}
