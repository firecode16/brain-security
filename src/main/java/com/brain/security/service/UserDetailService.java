package com.brain.security.service;

import com.brain.security.model.User;
import com.brain.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author firecode16
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usuarioRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not found: " + username);
        }
        return new CustomUserDetailService(user);
    }
}
