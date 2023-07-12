package org.sid.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
public class User extends AbstractUser{

}
