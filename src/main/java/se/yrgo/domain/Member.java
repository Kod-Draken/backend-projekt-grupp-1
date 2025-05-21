package se.yrgo.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Member {

    @Id
    private int memberId;

    private String name;

    private String phone;

    @ManyToMany
    private Set<Member> BookedClasses;

    public Member(int memberId, String name, String phone){
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
    }
    public Member() {}

    public String toString(){
        return this.memberId + ": " + this.name + " " + this.phone;
    }
    public int getMemberId(){
        return this.memberId;
    }
    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }

}
