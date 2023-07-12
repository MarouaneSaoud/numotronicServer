package org.sid.service;

import org.sid.dao.entity.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> managerlist();
    public Manager AddManager(Manager manager);
    public void delete(Long id);
}
