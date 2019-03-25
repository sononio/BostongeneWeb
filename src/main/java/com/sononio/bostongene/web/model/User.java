package com.sononio.bostongene.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * User entity
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Email
    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String passwordEnc;

    private String firstName;
    private String lastName;
    private Date birthday;

    public User(String email, String firstName, String lastName, String passwordEnc, Date birthday) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordEnc = passwordEnc;
        this.birthday = birthday;
    }

    public User() {
    }
}
