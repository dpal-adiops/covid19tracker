package com.adiops.apigateway.common.security.auth;

import org.springframework.security.core.Authentication;

/**
 * Interface for providing method to encode password
 * @author Deepak Pal
 *
 */
public interface AuthenticationFacade {
	Authentication getAuthentication();

	String encode(String password);
}
