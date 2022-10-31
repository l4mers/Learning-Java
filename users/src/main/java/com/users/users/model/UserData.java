package com.users.users.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Embeddable
public class UserData {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // berättar att Idet ska genereras av databsen
//    private Integer user_id;
    @Column(name = "firstname", table = "userdata")
    private String firstname;
    @Column(name = "lastname", table = "userdata")
    private String lastname;
    @Column(name = "birthdate", table = "userdata")
    private Date birthdate;
    @Column(name = "date", table = "userdata") //Eftersom variabel namnet och db namnet ej är samma
    private Timestamp timestamp;

    public UserData(String firstname, String lastname, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public UserData() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}