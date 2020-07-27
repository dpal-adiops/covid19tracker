package com.adiops.apigateway.profile.entity;


/**
 * Interface to user entity 
 * @author Deepak Pal
 *
 */
public interface User {

	String getPassword();

	String getUserName();

	String getLastName();

	String getFirstName();

	long getId();


}
