package com.users.users.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
@SecondaryTable(name= "userdata", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")) //Joinar tabell
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // berättar att Idet ska genereras av databsen
    private Integer id;
    private String username;
    private String password;
    private String email;

    @Embedded //Joinar tabellen
    UserData userData;

    public Users(String username, String password, String email, String firstname, String lastname, Date birthdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        userData = new UserData(firstname, lastname, birthdate);
    }

    public Users() {

    }

    public UserData getUserData() {
        return userData;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

