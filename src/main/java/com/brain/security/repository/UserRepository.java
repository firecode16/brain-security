package com.brain.security.repository;

import com.brain.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author firecode16
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
}
