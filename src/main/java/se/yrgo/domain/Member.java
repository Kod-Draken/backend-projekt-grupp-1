package se.yrgo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alrik
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String memberId;
    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String phone;

    @ManyToMany
    @JoinTable
    private Set<GymClass> bookedClasses;

    public Member(String memberId, String name, String phone){
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.bookedClasses = new HashSet<>();
    }
    public Member() {}

    public String toString(){
        return this.memberId + ": " + this.name + " " + this.phone;
    }
    public int getId() {return this.id;}
    public String getMemberId(){return this.memberId;}
    public String getName(){return this.name;}
    public String getPhone(){return this.phone;}
    public Set<GymClass> getAllBookedClasses(){return this.bookedClasses;}
    public void addBookedClass(GymClass gymClass){
        this.bookedClasses.add(gymClass);
    }
    public void removeBookedClass(GymClass gymClass){
        this.bookedClasses.remove(gymClass);
    }
    public void setName(String newName){this.name = newName;}
    public void setPhone(String newPhone){this.phone = newPhone;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id && Objects.equals(memberId, member.memberId) && Objects.equals(name, member.name) && Objects.equals(phone, member.phone) && Objects.equals(bookedClasses, member.bookedClasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberId, name, phone, bookedClasses);
    }
}