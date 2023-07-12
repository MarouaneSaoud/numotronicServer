package org.sid.dao.repository;

import org.sid.dao.entity.Particular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticularRepository extends JpaRepository<Particular,String> {
    Particular findParticularByName(String name);
}
