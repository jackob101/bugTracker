package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.DTO.Auth0Pojo;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.UserRepository;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        if (id != null && id > 0) {
            Optional<User> byId = userRepository.findById(id);
            return byId.orElse(null);
        }
        return null;
    }

    @Override
    public User save(User entity) {
        if (entity != null) {
            return userRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<User> saveAll(List<User> listOfEntities) {

        if (listOfEntities == null)
            throw new IllegalArgumentException("List cannot be null");
        if (listOfEntities.contains(null))
            throw new IllegalArgumentException("List cannot contain null value");
        return userRepository.saveAll(listOfEntities);
    }

    @Override
    public boolean delete(User entity) {
        if (entity != null) {
            userRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null && id > 0) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByIds(List<Long> assignedUsersId) {
        return userRepository.findAllById(assignedUsersId);
    }

    @Override
    public Boolean checkIfExist(Auth0Pojo auth0Pojo) {
        Optional<User> bySub = userRepository.findBySub(auth0Pojo.getSub());
        return bySub.isPresent();
    }

    @Override
    public User findBySub(String sub) {
        return userRepository.findBySub(sub).orElse(null);
    }

    @Override
    public List<User> findNotAssigned(Long issueId) {
        List<User> all = userRepository.findAll();
        return all.stream().filter(user -> user.getIssues()
                .stream()
                .filter(issue -> Objects.equals(issue.getId(), issueId))
                .findFirst().isEmpty()).collect(Collectors.toList());
    }


    private User createUser(Auth0Pojo auth0Pojo) {
        return User.builder()
                .nickname(auth0Pojo.getNickname())
                .name(auth0Pojo.getName())
                .emailVerified(auth0Pojo.isEmailVerified())
                .email(auth0Pojo.getEmail())
                .sub(auth0Pojo.getSub())
                .build();
    }
}
