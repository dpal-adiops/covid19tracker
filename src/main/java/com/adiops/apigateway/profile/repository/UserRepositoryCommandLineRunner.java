package com.adiops.apigateway.profile.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adiops.apigateway.profile.entity.UserEntity;

/**
 * Add the admin user to DB on application bootup.
 * @author Deepak Pal
 *
 */
@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		UserEntity user = new UserEntity();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setUserName("admin");
		user.setPassword("$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u");
		userRepository.save(user);
		log.info("New User is created : " + user);

		List<UserEntity> users = userRepository.findAll();
		log.info("All Users : " + users);

	}

}
