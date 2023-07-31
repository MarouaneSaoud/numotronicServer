package org.numo.dao.repository;

import org.numo.dao.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference,Long> {
    Reference findReferenceByName(String name);
}
