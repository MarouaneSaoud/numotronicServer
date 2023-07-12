package org.sid.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter

public class Particular {
    @Id
    private String name;
    private String altName;
    private String cin;
    private String address;
    private int postalcode;
    private String email;
    private String country;
}
