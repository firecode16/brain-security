package com.brain.security.model;

import com.brain.security.enums.RolEnum;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author firecode16
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterRequest {

    private String username;
    private String password;
    private Set<RolEnum> roles;
}
