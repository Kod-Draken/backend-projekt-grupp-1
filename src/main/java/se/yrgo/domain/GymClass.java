package se.yrgo.domain;

import java.time.LocalDateTime;
import java.util.List;


public class GymClass {
    private int id;
    private String name;
    private String description;
    private String roomName;
    private LocalDateTime scheduledAt;
    private List<Member> participants;
    private Instructor instructor;

}
