package org.sid.dao.repository;

import org.sid.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByAccount(Long id);
}
