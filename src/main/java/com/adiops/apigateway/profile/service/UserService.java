package com.adiops.apigateway.profile.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adiops.apigateway.profile.entity.User;
import com.adiops.apigateway.profile.resourceobject.UserRO;


/**
 * The user service interface responsible for all the operation related to profile.
 * @author Deepak Pal
 *
 */
public interface UserService {

	List<UserRO> findAll();


	

	User registerUser(User user);


	UserRO getCurrentUser();

}
