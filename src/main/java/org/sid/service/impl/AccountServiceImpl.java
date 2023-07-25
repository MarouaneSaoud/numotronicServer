package org.sid.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;
import org.sid.dao.repository.AppRoleRepository;
import org.sid.dao.repository.AppUserRepository;
import org.sid.error.BusinessException;
import org.sid.error.TechnicalException;
import org.sid.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<AppUser> allUser() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser saveUser(String username,String name, String password, String confirmedPassword) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null) throw new BusinessException("User already exists");
        if (!password.equals(confirmedPassword)) throw new TechnicalException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setName(name);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }
}
