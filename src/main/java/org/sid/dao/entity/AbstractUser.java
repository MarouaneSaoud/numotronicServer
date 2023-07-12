package org.sid.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractUser {
    @Id
    private Long id;
    private String Name;
    @OneToOne
    private AppUser account;
}
