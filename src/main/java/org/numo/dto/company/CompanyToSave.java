package org.numo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CompanyToSave {

    private String name;
    private String altName;
    private String cin;
    private String address;
    private int postalCode;
    private String department;
    private String email;
    private String website;
    private String skype;
    private int idrc;
    private int idif;
    private int patent;
    private int cnss;
    private String country;
    private String logo;
    private String password ;
}
