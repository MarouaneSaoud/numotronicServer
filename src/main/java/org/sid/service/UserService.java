package org.sid.service;

import org.sid.dao.entity.User;

import java.util.List;

public interface UserService {
    List<User> userlist();
    public User AddUser(User user);
    public void delete(Long id);
}
