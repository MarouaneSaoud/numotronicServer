package org.sid.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter

public class Company {
    @Id
    private String name;
    private String altName;
    private String address;
    private int postalcode;
    private String dep;
    private String email;
    private String website;
    private String skype;
    private int idrc;
    private int idif;
    private int idpatent;
    private int cnss;
    private String country;
    private String logo;

}
