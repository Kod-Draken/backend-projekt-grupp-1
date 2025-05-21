package se.yrgo.domain;

import javax.persistence.Id;
import java.util.List;

public class Instructor {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private List<GymClass> classesToInstruct;
}
