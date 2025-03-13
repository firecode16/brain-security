package com.brain.security.service;

import com.brain.security.model.Rol;
import com.brain.security.model.User;
import com.brain.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author firecode16
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User searchByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, email);
    }

    @Transactional
    public User saveUser(User user) {
        user.setUsername(user.getUsername());
        user.setRoles(user.getRoles());
        user.setPassword(user.getPassword());

        User status = userRepository.save(user);

        for (Rol rol : user.getRoles()) {
            rol.getUsers().add(status);
        }
        return status;
    }
}
