package com.trix.bugtracker.services.interfaces;

import com.trix.bugtracker.model.User.User;

import java.util.List;

public interface UserService extends CRUDService<User> {
    List<User> findAllByIds(List<Long> assignedUsersId);
}
