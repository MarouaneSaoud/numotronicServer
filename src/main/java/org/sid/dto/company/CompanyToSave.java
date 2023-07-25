package org.sid.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
@Data
@AllArgsConstructor

public class CompanyToSave {

    private  String name;
    private String altname;
    private String cin ;
    private String address;
    private int postalcode;
    private String departement ;
    private String email;
    private String website;
    private String skype ;
    private int idrc ;
    private int idif;
    private int patent;
    private int cnss ;
    private String country;
    private String logo;
}
