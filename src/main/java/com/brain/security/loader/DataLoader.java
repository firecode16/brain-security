package com.brain.security.loader;

import com.brain.security.model.Rol;
import com.brain.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author firecode16
 */
@Configuration
public class DataLoader {

    @Autowired
    private RolRepository rolRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            loadRolesByDefault();
        };
    }

    private void loadRolesByDefault() {
        if (!rolRepository.existsByRolName("ROLE_USER")) {
            Rol userRol = new Rol();
            userRol.setRolName("ROLE_USER");
            rolRepository.save(userRol);
        }

        if (!rolRepository.existsByRolName("ROLE_ADMIN")) {
            Rol adminRol = new Rol();
            adminRol.setRolName("ROLE_ADMIN");
            rolRepository.save(adminRol);
        }
    }
}
