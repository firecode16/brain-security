package com.brain.security.controller;

import com.brain.security.config.JwtConfig;
import com.brain.security.enums.RolEnum;
import com.brain.security.model.AuthRequest;
import com.brain.security.model.AuthResponse;
import com.brain.security.model.RegisterRequest;
import com.brain.security.model.Rol;
import com.brain.security.model.User;
import com.brain.security.repository.RolRepository;
import com.brain.security.service.UserDetailService;
import com.brain.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firecode16
 */
@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/login")
    public ResponseEntity<?> sessionLogin(@RequestBody AuthRequest authRequest) {
        UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getUsername());

        //check if user exists and password is OK
        if (userDetails != null && passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            String jwt = jwtConfig.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is bad");
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.searchByName(registerRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is currently in Use");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<Rol> roles = new HashSet<>();

        if (registerRequest.getRoles() != null) {
            for (RolEnum rolEnum : registerRequest.getRoles()) {
                Rol rolObj = rolRepository.findByRolName(rolEnum.name());
                if (rolObj != null) {
                    roles.add(rolObj);
                }
            }
            user.setRoles(roles);
        }

        // Si no se proporcionan roles válidos, asignar el rol por defecto de usuario
        if (roles.isEmpty()) {
            Rol userRole = rolRepository.findByRolName(RolEnum.ROLE_USER.getRol());
            roles.add(userRole);
            user.setRoles(roles);
        }

        userService.saveUser(user);
        return ResponseEntity.ok().body("{\"message\": \"The User was successfully registered\"}");
    }
}
