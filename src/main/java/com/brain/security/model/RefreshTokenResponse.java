package com.brain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Firecode
 */
@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenResponse {

    private String refreshToken;
}
