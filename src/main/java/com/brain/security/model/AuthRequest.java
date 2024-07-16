package com.brain.security.model;

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
public class AuthRequest {

    private String username;
    private String password;
}
