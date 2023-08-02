package org.numo.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CompanyToSave {

    public String name;
    public String altName;
    public String cin;
    public String address;
    public int postalCode;
    public String department;
    public String email;
    public String website;
    public String skype;
    public int idrc;
    public int idif;
    public int patent;
    public int cnss;
    public String country;
    public String logo;
}
