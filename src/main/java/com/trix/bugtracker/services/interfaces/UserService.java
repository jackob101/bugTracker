package com.trix.bugtracker.services.interfaces;

import com.trix.bugtracker.DTO.Auth0Pojo;
import com.trix.bugtracker.model.User.User;

import java.util.List;

public interface UserService extends CRUDService<User> {
    List<User> findAllByIds(List<Long> assignedUsersId);

    Boolean checkIfExist(Auth0Pojo auth0Pojo);

    User findBySub(String sub);

    List<User> findNotAssigned(Long issueId);
}
