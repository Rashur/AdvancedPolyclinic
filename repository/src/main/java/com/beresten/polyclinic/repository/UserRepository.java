package com.beresten.polyclinic.repository;

import com.beresten.polyclinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findUserByUsernameOrEmail(String username, String email);
}
