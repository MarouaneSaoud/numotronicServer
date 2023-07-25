package org.sid.dto.user;

import lombok.Data;

@Data
public class UserForm {
    private String username;
    private String name;
    private String password;
    private String confirmedPassword;
}
