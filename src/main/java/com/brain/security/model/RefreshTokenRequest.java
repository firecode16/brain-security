package com.brain.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Firecode
 */
@Getter
@Setter
@ToString
public class RefreshTokenRequest {

    private String token;
}
