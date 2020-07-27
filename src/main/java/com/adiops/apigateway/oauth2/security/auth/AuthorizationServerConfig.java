package com.adiops.apigateway.oauth2.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * This class extends AuthorizationServerConfigurerAdapter and is responsible
 * for generating tokens specific to a client.
 * 
 * @author Deepak Pal
 *
 */

@Configuration
//Enables an authorization server.
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String CLIEN_ID = "app-client";
	// static final String CLIENT_SECRET = "devglan-secret";
	private static final String CLIENT_SECRET = "$2a$04$wA1IKvF4bjX5Hp5VbSmM9.UdOxu1zHFgdqYz/wa2SiRwz81FElrkm";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String TRUST = "trust";
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	private static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("as466gf");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * ClientDetailsServiceConfigurer : a configurer that defines the client details
	 * service. Client details can be initialized, or you can just refer to an
	 * existing store.
	 * 
	 * ClientDetailsServiceConfigurer can be used to define an in-memory or JDBC
	 * implementation of the client details service. here, We are using an in-memory
	 * implementation.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer.inMemory().withClient(CLIEN_ID).secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST).accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	/**
	 *
	 * AuthorizationServerEndpointsConfigurer : defines the authorization and token
	 * endpoints and the token services.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());
	}
}
