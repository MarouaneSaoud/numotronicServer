package org.numo.dao.repository;

import org.numo.dao.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    @Query("SELECT u FROM AppUser u JOIN u.roles r WHERE r.roleName= 'admin'")
    List<AppUser> findAdminUsers();

}
