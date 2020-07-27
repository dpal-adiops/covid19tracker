package com.adiops.apigateway.common.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This claass provide the implementaion of Authentication Facade and responsible for encoding the password.  
 * @author Deepak Pal
 *
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Autowired
	BCryptPasswordEncoder mBCryptPasswordEncoder;
	 @Override
	    public Authentication getAuthentication() {
	        return SecurityContextHolder.getContext().getAuthentication();
	    }
	 @Override
	public  String encode(String password)
	 {
		 return mBCryptPasswordEncoder.encode(password);
	 }
}
