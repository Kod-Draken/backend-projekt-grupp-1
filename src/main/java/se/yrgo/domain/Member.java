package se.yrgo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {

    @Id
    private String memberId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Member() {}
    public Member(String memberId, String name, String email, String phone, String address){}

}
