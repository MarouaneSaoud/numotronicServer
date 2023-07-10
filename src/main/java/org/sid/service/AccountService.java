package org.sid.service;

import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;

import java.util.List;

public interface AccountService {
    List<AppUser> allUser();
    public AppUser saveUser(String username,String password,String confirmedPassword);
    public AppRole saveRole(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
}
