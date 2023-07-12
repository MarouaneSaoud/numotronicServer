package org.sid.dao.repository;

import org.sid.dao.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Manager findManagerByAccount (Long id);
}
