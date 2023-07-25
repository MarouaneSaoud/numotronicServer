package org.sid.service;

import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;

import java.util.List;

public interface AccountService {
    List<AppUser> allUser();
    AppUser saveUser(String username , String name,String password,String confirmedPassword);
    AppRole saveRole(AppRole role);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username,String rolename);
}
