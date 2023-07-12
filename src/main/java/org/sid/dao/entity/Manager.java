package org.sid.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public class Manager extends AbstractUser {
    @OneToMany
    private List<Company> companies;
}
