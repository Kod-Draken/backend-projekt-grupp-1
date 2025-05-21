package se.yrgo.domain;

import javax.persistence.*;
import java.util.Set;

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
    private Set<Member> BookedClasses;

    public Member(String memberId, String name, String phone){
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
    }
    public Member() {}

    public String toString(){
        return this.memberId + ": " + this.name + " " + this.phone;
    }
    public String getMemberId(){
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
