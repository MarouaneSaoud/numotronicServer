package org.sid.dao.repository;

import org.sid.dao.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {
    Company findCompanyByName (String name);
}
