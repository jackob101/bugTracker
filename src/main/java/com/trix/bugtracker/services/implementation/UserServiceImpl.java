package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.UserRepository;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
