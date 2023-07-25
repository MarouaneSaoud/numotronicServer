package org.sid.dao.repository;

import org.sid.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,String> {
}
