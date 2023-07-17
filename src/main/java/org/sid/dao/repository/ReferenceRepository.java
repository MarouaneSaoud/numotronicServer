package org.sid.dao.repository;

import org.sid.dao.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference,Long> {
    Reference findReferenceByName(String name);
}
