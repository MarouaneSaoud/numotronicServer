package org.sid.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractUtilisateur {
    @Id
    private Long identifiant;
    private String email;
    private String tel;
    @OneToOne
    private AppUser account;
}
