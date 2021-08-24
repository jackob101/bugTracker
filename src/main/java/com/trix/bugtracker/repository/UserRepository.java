package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySub(String sub);


//    @Query("SELECT user FROM User user JOIN user.issues issue where issue.id <> :id")
//    List<User> findByNotAssigned(Long id);


}
