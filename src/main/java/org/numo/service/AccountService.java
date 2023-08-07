package org.numo.service;

import org.numo.dao.entity.AppRole;
import org.numo.dao.entity.AppUser;

import java.util.List;

public interface AccountService {
    List<AppUser> allUser();
    AppUser saveUser(String username , String name,String password,String confirmedPassword);
    AppRole saveRole(AppRole role);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username,String rolename);
    List<AppUser> findAdminUsers();
    Boolean DisableUser (Long id);
}
