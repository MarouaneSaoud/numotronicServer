package org.numo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CompanyToSave {

    private String name;
    private String altName;
    private String cin_rc;
    private String address;
    private String tel;
    private int postalCode;
    private String department;
    private String email;
    private String website;
    private String skype;
    private int idif;
    private int patent;
    private int cnss;
    private String country;
    private String password ;
}
