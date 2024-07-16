package com.brain.security.repository;

import com.brain.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author firecode16
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByRolName(String rolName);

    boolean existsByRolName(String rolName);
}
