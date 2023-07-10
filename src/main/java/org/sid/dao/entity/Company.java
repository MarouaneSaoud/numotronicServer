package org.sid.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Company extends AbstractUtilisateur{
    private String denomiation;
}
