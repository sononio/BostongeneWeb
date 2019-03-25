package com.sononio.bostongene.web.api.users;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Container for user data in API.
 */
@Data
public class UserBodyContainer {
    @Email private String email;
    @NotNull private String password;
    @NotNull private String firstName;
    @NotNull private String lastName;
    private Date birthday;
}
