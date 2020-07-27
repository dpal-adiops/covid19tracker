package com.adiops.apigateway.profile.resource;

import java.util.List;

import org.apache.commons.codec.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adiops.apigateway.common.response.ResponseStatusConstants;
import com.adiops.apigateway.common.response.RestException;
import com.adiops.apigateway.profile.resourceobject.UserRO;
import com.adiops.apigateway.profile.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * This is class for rest resource from user information. 
 * @author Deepak Pal
 *
 */
@SwaggerDefinition(basePath = "users", info = @Info(version = "1.0", title = "users", description = "This REST resource defines various  operations for user objects"))
@Api("users")
@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	/**
	 * Get a list of Users
	 * @return List<UserRO>
	 */
	@ApiOperation(value = "Get a list of Users")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.OK, message = "users fetched.", response = UserRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public List<UserRO> listUser() {
		return userService.findAll();
	}

	/**
	 * Register a user
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "Register a user")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.CREATED, message = "User has been registered.", response = UserRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = 412, message = "Precondition Failed", response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody UserRO user) {
		user = (UserRO) userService.registerUser(user);
		return ResponseEntity.ok("User has been registered");
	}

	/**
	 * Get user detail
	 * @return UserRO
	 */
	@ApiOperation(value = "Get user detail")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.OK, message = "user details fetched.", response = UserRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public UserRO getUserDetails() {
		return userService.getCurrentUser();
	}

	@RequestMapping(value = "/health", method = RequestMethod.GET, produces = { "application/json" })
	public String healthCheck()
	{
		return "{status: \"running\"}";
	}
}
