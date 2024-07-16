package com.brain.security.model;

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
public class AuthResponse {

    private String token;
}
