package com.brain.security.enums;

/**
 *
 * @author firecode16
 */
public enum RolEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String rol;

    RolEnum(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
